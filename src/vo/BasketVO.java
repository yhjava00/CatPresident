package vo;

public class BasketVO {
	private int idx;
	private String member_id;
	private int goods_idx; 
	private int quantity;
	
	private String name;
	private String img;
	private int price;
	private int sale;
	private String catecory;
	private int del;
	
	public int getGoods_idx() {
		return goods_idx;
	}

	public void setGoods_idx(int goods_idx) {
		this.goods_idx = goods_idx;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getCatecory() {
		return catecory;
	}

	public void setCatecory(String catecory) {
		this.catecory = catecory;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String toString() {
		return idx + member_id + goods_idx ;
		
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getGood_id() {
		return goods_idx;
	}
	public void setGood_id(int good_id) {
		this.goods_idx = good_id;
	}
	public int getQuanity() {
		return quantity;
	}
	public void setQuanity(int quanity) {
		this.quantity = quanity;
	}
	
}
