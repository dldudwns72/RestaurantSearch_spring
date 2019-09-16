package study.spring.springhelper.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

// My_Batis 연결을 위한 Restaurant 빈즈
@Data
public class ReserveCheck {

	@SerializedName("checkNo")
	public int checkNo;
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("checkdate")
	public String checkdate;
	
		
	public int getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}

	
	
		public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}


		public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

		// 페이지 구현이 필요한 경우 아래 속성도 추가한다.
		// SQL의 limit 절에 사용될 변수
		private static int offset;
		private static int listCount;

		public static int getOffset() {
			return offset;
		}

		public static void setOffset(int offset) {
			ReserveCheck.offset = offset;
		}

		public static int getListCount() {
			return listCount;
		}

		public static void setListCount(int listCount) {
			ReserveCheck.listCount = listCount;
		}

}
