package vo;

public class GoodsDetailsVO {
	private String name;
	private int price;
	private String description;
	private String detailsimg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetailsimg() {
		return detailsimg;
	}
	public void setDetailsimg(String detailsimg) {
		this.detailsimg = detailsimg;
	}
	
	@Override
	public String toString() {
		return this.name + " , " + this.price + " , " + this.description + " , " + this.detailsimg;
	}
}
