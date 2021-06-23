package vo;

public class GoodsVO {

	private int idx;
	private String name;
	private String img;
	private int price;
	private int quantity;
	private String category;
	private float result_score;
	private int vote_num;
	
	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public float getResult_score() {
		return result_score;
	}


	public void setResult_score(float result_score) {
		this.result_score = result_score;
	}


	public int getVote_num() {
		return vote_num;
	}


	public void setVote_num(int vote_num) {
		this.vote_num = vote_num;
	}


	@Override
	public String toString() {
		return this.idx + " , " + this.img + " , " + this.name + " , " + this.price + " , " + this.quantity + " , " + this.category + " , " + this.result_score + " , " + vote_num;
	}
}
