package study.spring.springhelper.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

// My_Batis 연결을 위한 Restaurant 빈즈
@Data
public class Restaurants {

	// 전체 건수
	@SerializedName("total")
	public int total;
	@SerializedName("start")
	public int start;
	// 검색되는 개수
	@SerializedName("display")
	public int display;

	@SerializedName("items")
	public List<Items> items;
	
	public static class Items {
		@SerializedName("restNo")
		public int restNo;
		@SerializedName("title")
		public String title;
		@SerializedName("link")
		public String link;
		@SerializedName("category")
		public String category;
		@SerializedName("description")
		public String description;
		@SerializedName("telephone")
		public String telephone;
		@SerializedName("address")
		public String address;
		@SerializedName("roadAddress")
		public String roadAddress;
		@SerializedName("mapx")
		public int mapx;
		@SerializedName("mapy")
		public int mapy;
		
		// 페이지 구현이 필요한 경우 아래 속성도 추가한다.
		// SQL의 limit 절에 사용될 변수
		private static int offset;
		private static int listCount;

		public static int getOffset() {
			return offset;
		}

		public static void setOffset(int offset) {
			Restaurants.Items.offset = offset;
		}

		public static int getListCount() {
			return listCount;
		}

		public static void setListCount(int listCount) {
			Restaurants.Items.listCount = listCount;
		}

		public int getRestNo() {
			return restNo;
		}

		public void setRestNo(int restNo) {
			this.restNo = restNo;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getRoadAddress() {
			return roadAddress;
		}

		public void setRoadAddress(String roadAddress) {
			this.roadAddress = roadAddress;
		}

		public int getMapx() {
			return mapx;
		}

		public void setMapx(int mapx) {
			this.mapx = mapx;
		}

		public int getMapy() {
			return mapy;
		}

		public void setMapy(int mapy) {
			this.mapy = mapy;
		}

		@Override
		public String toString() {
			return "정보 [restNo=" + restNo + ", title=" + title + ", link=" + link + ", category=" + category
					+ ", description=" + description + ", telephone=" + telephone + ", address=" + address
					+ ", roadAddress=" + roadAddress + ", mapx=" + mapx + ", mapy=" + mapy + "]";
		}

	}

}
