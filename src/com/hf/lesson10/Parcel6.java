package com.hf.lesson10;

public class Parcel6 {
	private void internalTracking(boolean b) {
		if(b) {
			// 此处访问权限必须为default
			class TrackingSlip{
				private String id;
				TrackingSlip(String s) {
					this.id=s;
				}
				String getSlip() {return id;}
			}
			TrackingSlip trackingSlip = new TrackingSlip("slip");
			String slip = trackingSlip.getSlip();
			System.out.println(slip);
			System.out.println(trackingSlip.id);
		}
	}
	public static void main(String[] args) {
		Parcel6 parcel6=new Parcel6();
		parcel6.internalTracking(true);
	}
}
