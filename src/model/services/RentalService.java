package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	private BrasilTaxService taxService;
	
	public RentalService(Double pricePerHour, Double pricePerDay, BrasilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hour = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double basicPayment;
		if(hour <= 12.0) {
			basicPayment = Math.ceil(hour) * pricePerHour;
		}else {
			basicPayment = Math.ceil(hour) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
