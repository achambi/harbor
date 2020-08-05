package bo.com.mondongo.harbor;

import bo.com.mondongo.harbor.entity.Doctor;
import bo.com.mondongo.harbor.entity.Hospital;
import bo.com.mondongo.harbor.entity.Speciality;
import bo.com.mondongo.harbor.repository.IDoctorRepository;
import bo.com.mondongo.harbor.repository.IHospitalRepository;
import bo.com.mondongo.harbor.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseSeeder {
    private final ISpecialityRepository specialityRepository;
    private final IDoctorRepository doctorRepository;
    private final IHospitalRepository hospitalRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataBaseSeeder(ISpecialityRepository specialityRepository,
                          IDoctorRepository doctorRepository,
                          IHospitalRepository hospitalRepository,
                          JdbcTemplate jdbcTemplate) {
        this.specialityRepository = specialityRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("unused")
    @EventListener
    public void seed(ContextRefreshedEvent event) throws ParseException {
        seedSpecialities();
        seedHospitals();
        seedDoctorsTable();
    }

    private void seedSpecialities() {
        String sql = "SELECT * FROM specialities s";
        List<Speciality> specialities = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (specialities.size() <= 0) {
            System.out.println("Specialities table seeding.....");
            Speciality s1 = new Speciality("Medicina General", "Medicina General");
            Speciality s2 = new Speciality("Dermatologia", "Dermatologia");
            Speciality s3 = new Speciality("Cardiologia", "Cardiologia");
            Speciality s4 = new Speciality("Ginecologia", "Ginecologia");
            specialityRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
            System.out.println("Specialities table seeded");
        } else {
            System.out.println("Specialities Seeding Not Required");
        }
    }

    private void seedHospitals() {
        String sql = "SELECT * FROM hospitals s";
        List<Hospital> hospitals = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (hospitals.size() <= 0) {
            System.out.println("Hospitals table seeding.....");
            Hospital h1 = new Hospital("Hospital General", "Hospital General");
            Hospital h2 = new Hospital("Cies", "Cies");
            Hospital h3 = new Hospital("Hospital Obrero", "Cardiologia");
            hospitalRepository.saveAll(Arrays.asList(h1, h2, h3));
            System.out.println("Hospitals table seeded");
        } else {
            System.out.println("Hospitals Seeding Not Required");
        }
    }

    private void seedDoctorsTable() throws ParseException {
        Hospital hospital = hospitalRepository.findByName("Cies");
        String sql = "SELECT * FROM people p WHERE p.type = 'doctor'";
        List<Doctor> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs.size() <= 0) {
            System.out.println("Doctors table seeding.....");
            Doctor d1 = new Doctor("Jose Maria", "Mendoza",
                                   "12/12/1990", "Zona 1 de Mayo, Calle Wilacora, #123", hospital
            );
            Doctor d2 = new Doctor("Juan Jose", "Alarcon Espejo",
                                   "12/31/1988", "Calle Murillo #2020, Zona Villa Fatima", hospital
            );
            Doctor d3 = new Doctor("Milenka", "Zoto Conde",
                                   "08/14/1987", "Zona 1 de Mayo, Calle Wilacora, #123", hospital
            );
            Doctor d4 = new Doctor("Maria Jose", "Chambi Nuñez",
                                   "01/19/1970", "Calle Murillo #2020, Zona Villa Fatima", hospital
            );

            doctorRepository.saveAll(Arrays.asList(d1, d2, d3, d4));
            System.out.println("Doctors table seeded");
        } else {
            System.out.println("Sections Doctors Not Required.");
        }
    }
}
