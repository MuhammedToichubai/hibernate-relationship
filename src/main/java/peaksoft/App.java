package peaksoft;

import peaksoft.entities.Address;
import peaksoft.entities.Company;
import peaksoft.entities.Project;
import peaksoft.service.impl.AddressService;
import peaksoft.service.impl.CompanyService;
import peaksoft.service.impl.ProjectService;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        CompanyService companyService = new CompanyService();
        ProjectService projectService = new ProjectService();

        Address address = Address.builder()
                .country("Kyrgyzstan")
                .city("Bishkek")
                .build();



        while (true){
            System.out.println("Write commands: ");
            switch (new Scanner(System.in).nextInt()){
                case 1 ->{
                    System.out.println(addressService.save(address));
                }
                case 2 ->{
                    Company company = Company.builder().name("SpaceX").build();
                    Address add = Address.builder().country("Kyrgyzstan")
                                    .city("Talas").build();
                    company.setAddress(add);
                    add.setCompany(company);

                    System.out.println(addressService.save(add));
                }
                case 3 ->{
                    System.out.println("write address id: ");
                    Long addressId = new Scanner(System.in).nextLong();
                    String message = companyService.saveCompany(addressId, new Company("Google"));
                    System.out.println(message);
                }

                case 4 ->{
                    System.out.println("write address id: ");
                    Long addressId = new Scanner(System.in).nextLong();
                    System.out.println("write company id: ");
                    Long companyId = new Scanner(System.in).nextLong();
                    companyService.assignCompanyToAddress(addressId, companyId);
                }
                case 5 ->{
                    System.out.println("Write address id: ");
                    Long addressId = new Scanner(System.in).nextLong();
                    addressService.deleteByID(addressId);
                }
                case 6 -> {
                    System.out.println("Write company id: ");
                    Long comId = new Scanner(System.in).nextLong();
                    System.out.println(companyService.deleteByID(comId));
                }
                case 7->{
                    System.out.println(projectService.save(Project.builder().title("LMS").build()));
                }
                case 8->{
                    System.out.println(projectService.save(1L, Project.builder().title("eBook").build()));
                }
                case 0 ->{
                    addressService.findAll().forEach(System.out::println);
                }
            }
        }



    }
}
