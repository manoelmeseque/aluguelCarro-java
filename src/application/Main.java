package application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalService;

public class Main {

	public static void main(String[] args) throws ParseException  {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");	
		
		System.out.println("Entre com o aluguel");
		System.out.print("Modelo do carro: ");
		String model = sc.next();
		System.out.print("Saiu (dd/MM/yyyy hh:mm): ");
		sc.nextLine();
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Retornou (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Preço por hora: R$");
		double hora = sc.nextDouble();
		System.out.print("Preço por dia: R$");
		double dia = sc.nextDouble();
		
		RentalService rs = new RentalService(hora, dia, new BrasilTaxService());
		rs.processInvoice(cr);
		
		System.out.println("Fatura");
		System.out.println("Pagamento base: R$" + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total: R$" + String.format("%.2f", cr.getInvoice().totalPayment()));
		
	}

}
