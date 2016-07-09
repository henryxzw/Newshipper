package com.femto.shipper.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.femto.shipper.R;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.URIUtils;

/**
 * 获取图像
 * 
 * @author Whunf
 * 
 */
@SuppressLint({ "InflateParams", "ValidFragment" })
public class GetPictureFragment extends DialogFragment
{
	// 注册（1:1的裁剪）
	public static final int TYPE_REGIST = 22;
	// 其他
	public static final int TYPE_OTHER = 2;
	// 拍照之后的地址
	private Uri picUri;
	// 裁减之后的地址
	private Uri imageUri;
	private static final int CHOOSE_BIG_PICTURE = 1;// 本地图片请求码
	private static final int TACK_PICTURE = 2;// 相机拍照请求码

	public GetPictureFragment(OnGetPictureListener onGetPictureListener)
	{
		this.onGetPictureListener = onGetPictureListener;
	}

	/**
	 * 单例模式
	 * 
	 * @param type
	 *            是否按比例剪切图片
	 * @param isTake
	 *            是否是拍照
	 * @return
	 */
	@SuppressLint("ValidFragment")
	public static GetPictureFragment newInstance(int type, boolean isTake, OnGetPictureListener onGetPictureListener)
	{
		GetPictureFragment fragment = new GetPictureFragment(onGetPictureListener);
		Bundle b = new Bundle();
		b.putInt("type", type);
		b.putBoolean("isTake", isTake);
		fragment.setArguments(b);
		return fragment;
	}

	/**
	 * 获取当前的类型
	 * 
	 * @return
	 */
	private int getPictureType()
	{
		return getArguments().getInt("type");
	}

	/**
	 * 获取是否是拍照
	 * 
	 * @return
	 */
	private boolean isTake()
	{
		return getArguments().getBoolean("isTake");
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setCancelable(true);// 表示当前的弹出框可以用返回键返回
		setStyle(STYLE_NO_TITLE, 0);// 设置弹出框没有标题(主题默认(可以用自定主题))
		initCacheFolder();
		// 剪辑之后的图片的缓存路径
		imageUri = Uri.parse("file://" + URIUtils.URI_PIC + "/" + System.currentTimeMillis() + ".jpg");
		if (isTake())
		{

			startCamera();
			LogUtils.i("------------------>>>>" + "相机");
		} else
		{
			LogUtils.i("------------------>>>>" + "相册");
			getFromPictureHome();
		}
	}

	/**
	 * 初始化缓存目录
	 */
	private void initCacheFolder()
	{
		// 检测缓存目录是否可用
		if (URIUtils.checkCacheFolder())
		{
			picUri = Uri.parse("file://" + URIUtils.URI_PIC + "/t001.jpg");// 没有剪辑的缓存
		} else
		{
			// URIUtils_Lin.showToast(getActivity(), "本地文件不可写");
			dismiss();// 关闭弹出框
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.fragment_get_picture, null);
		return layout;
	}

	private void startCamera()
	{
		// 调用系统的拍照功能
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
		intent.putExtra("noFaceDetection", false);
		startActivityForResult(intent, TACK_PICTURE);
	}

	// 从图库获取并裁减
	private void getFromPictureHome()
	{
		// Intent intent = new Intent(Intent.ACTION_GET_CONTENT,null);-----打开本地相册
		Intent intent = new Intent(Intent.ACTION_PICK, null);// /-----打开本地相册，并启动剪切
		// 如果外面调用此类new出来的实例标签是TYPE_REGIST的话就按1比1剪辑。
		if (getPictureType() == TYPE_REGIST)
		{
			// aspectX aspectY 是宽高的比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY 是裁剪图片宽高
			intent.putExtra("outputX", 299);
			intent.putExtra("outputY", 299);
		}
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		intent.putExtra("outputFormat", "JPEG");
		startActivityForResult(intent, CHOOSE_BIG_PICTURE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		LogUtils.i("onActivityResult ... ");
		// 调用系统的Activity后返回的结果码如果是Activity.RESULT_OK。否则表示没有选择图片就点返回键
		if (resultCode == Activity.RESULT_OK)
		{
			switch (requestCode)
			{
			case TACK_PICTURE:
				LogUtils.i("onActivityResult   TACK_PICTURE ");

				// 将拍照之后的图像进行裁剪，这里启动裁剪的Activity
				Intent intent = new Intent("com.android.camera.action.CROP");
				// 如果外面调用此类new出来的实例标签是TYPE_REGIST的话就按1比1剪辑。
				if (getPictureType() == TYPE_REGIST)
				{
					// aspectX aspectY 是宽高的比例
					intent.putExtra("aspectX", 1);
					intent.putExtra("aspectY", 1);
					// outputX outputY 是裁剪图片宽高
					intent.putExtra("outputX", 299);
					intent.putExtra("outputY", 299);
				}
				intent.setDataAndType(picUri, "image/*");
				intent.putExtra("crop", "true");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				intent.putExtra("outputFormat", "JPEG");
				intent.putExtra("return-data", false);
				// 拍照完成后。调用了自己的Result的方法。到requestCode==CHOOSE_BIG_PICTURE
				startActivityForResult(intent, CHOOSE_BIG_PICTURE);
				break;
			case CHOOSE_BIG_PICTURE:// 拍照完成到这里调用
				LogUtils.i("onActivityResult   CHOOSE_BIG_PICTURE ");
				if (imageUri != null)
				{
					if (null != onGetPictureListener)
					{
						onGetPictureListener.onComplete(imageUri);
					}
				}
				/**
				 * 在该活动获得系统Activity返回的结果。处理完再返回结果回到注册界面的时候记得把当前自己的弹出框关闭掉。
				 * 但是注意的是，必须调用玩回调返回结果后再调用该方法关闭自己。否则自己
				 */
				GetPictureFragment.this.dismiss();// 在该活动获得系统Activity返回的结果。处理玩再返回结果回到注册界面的时候记得把当前自己的弹出框关闭掉。
				break;
			}
		} else
		{// 如果用户打开之后没有选择图片就返回。则证明用户取消了选择。实现取消的接口
			LogUtils.i("没有裁剪!!!");
			if (null != onGetPictureListener)
			{
				onGetPictureListener.onCancel();
			}
			GetPictureFragment.this.dismiss();// 在该活动获得系统Activity返回的结果。处理玩再返回结果回到注册界面的时候记得把当前自己的弹出框关闭掉。
		}

	}

	/**
	 * 头像选择接口的回调
	 */
	private OnGetPictureListener onGetPictureListener;

	public OnGetPictureListener getOnGetPictureListener()
	{
		return onGetPictureListener;
	}

	public void setOnGetPictureListener(OnGetPictureListener onGetPictureListener)
	{
		this.onGetPictureListener = onGetPictureListener;
	}

	/**
	 * 返回结果的监听器
	 * 
	 * @author Whunf
	 * 
	 */
	public interface OnGetPictureListener
	{
		/**
		 * 头像选择成功！
		 * 
		 * @param result
		 */
		void onComplete(Uri result);

		/**
		 * 头像选择取消了。
		 */
		void onCancel();
	}
}
