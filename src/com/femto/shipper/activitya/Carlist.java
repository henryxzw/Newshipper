package com.femto.shipper.activitya;

import java.util.List;

public class Carlist {
	public String status;
	public String msg;
	public List<carlistza> list;

	public class carlistza {
		public String id;
		public String title;
		public String small_img_url;
		public List<carlistzb> sub_title;

		public class carlistzb {
			public String id;
			public String title;
			public String big_img_url;
			public List<carlistzc> sub_title;

			public class carlistzc {
				public String id;
				public String title;
				public String amount;
				public String max_load;
				public String max_tonnage;
				public String start_momey;
				public String exceed_momey;
			}
		}
	}
}