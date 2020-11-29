package m07.entity;

public class ViewReceiptionDetail {

	private int stt;
	private int idSp;
	private String dvt;
	private int soluong;
	private double dongia;
	
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	public int getIdSp() {
		return idSp;
	}
	public void setIdSp(int idSp) {
		this.idSp = idSp;
	}
	public String getDvt() {
		return dvt;
	}
	public void setDvt(String dvt) {
		this.dvt = dvt;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getDongia() {
		return dongia;
	}
	public void setDongia(double dongia) {
		this.dongia = dongia;
	}
	public ViewReceiptionDetail(int stt, int idSp, String dvt, int soluong, double dongia) {
		super();
		this.stt = stt;
		this.idSp = idSp;
		this.dvt = dvt;
		this.soluong = soluong;
		this.dongia = dongia;
	}
	public ViewReceiptionDetail() {
		
	}
}
