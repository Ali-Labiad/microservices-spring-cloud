//package com.ibm.bootcamp.spring.datalake;
//
//import com.ibm.bootcamp.spring.datalake.db.UserRepository;
//import com.ibm.bootcamp.spring.datalake.model.Address;
//import com.ibm.bootcamp.spring.datalake.model.Company;
//import com.ibm.bootcamp.spring.datalake.model.Geo;
//import com.ibm.bootcamp.spring.datalake.model.User;
//import org.junit.jupiter.api.Assertions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public class UserTests extends AbstractTest<User, User> {
//
//    @Autowired
//    UserRepository repo;
//
//    @Override
//    User getModel(long id) {
//        User u = new User();
//        Address a = new Address();
//        Company c = new Company();
//        Geo g = new Geo();
//
//        a.setCity(s());
//        a.setStreet(s());
//        a.setSuite(s());
//        a.setZipcode(s());
//
//        c.setBs(s());
//        c.setName(s());
//        c.setCatchPhrase(s());
//
//        g.setLat(f());
//        g.setLng(f());
//
//        u.setId(id);
//        u.setAddress(a);
//        u.setCompany(c);
//        u.setEmail(s());
//        u.setGeo(g);
//        u.setName(s());
//        u.setPhone(s());
//        u.setUsername(s());
//        u.setWebsite(s());
//        return u;
//    }
//
//    @Override
//    User getDto(long id) {
//        return new User()
//                .id(id)
//                .address(new UserAddress()
//                        .city(s())
//                        .geo(new UserAddressGeo()
//                                .lat(String.valueOf(f()))
//                                .lng(String.valueOf(f()))
//                        )
//                        .street(s())
//                        .suite(s())
//                        .zipcode(s()))
//                .company(new UserCompany()
//                        .bs(s())
//                        .name(s())
//                        .catchPhrase(s()))
//                .email(s())
//                .name(s())
//                .phone(s())
//                .username(s())
//                .website(s());
//    }
//
//    @Override
//    String topic() {
//        return "users-v1";
//    }
//
//    @Override
//    void assertEquals(com.ibm.bootcamp.spring.users.model.User dto, User model) {
//        Assertions.assertEquals(dto.getId(), model.getId());
//        Assertions.assertEquals(dto.getEmail(), model.getEmail());
//        Assertions.assertEquals(dto.getName(), model.getName());
//        Assertions.assertEquals(dto.getPhone(), model.getPhone());
//        Assertions.assertEquals(dto.getUsername(), model.getUsername());
//        Assertions.assertEquals(dto.getWebsite(), model.getWebsite());
//        Assertions.assertEquals(dto.getAddress().getCity(), model.getAddress().getCity());
//        Assertions.assertEquals(dto.getAddress().getStreet(), model.getAddress().getStreet());
//        Assertions.assertEquals(dto.getAddress().getSuite(), model.getAddress().getSuite());
//        Assertions.assertEquals(dto.getAddress().getZipcode(), model.getAddress().getZipcode());
//        Assertions.assertEquals(Float.valueOf(dto.getAddress().getGeo().getLat()), model.getGeo().getLat());
//        Assertions.assertEquals(Float.valueOf(dto.getAddress().getGeo().getLng()), model.getGeo().getLng());
//        Assertions.assertEquals(dto.getCompany().getBs(), model.getCompany().getBs());
//        Assertions.assertEquals(dto.getCompany().getCatchPhrase(), model.getCompany().getCatchPhrase());
//        Assertions.assertEquals(dto.getCompany().getName(), model.getCompany().getName());
//    }
//
//    @Override
//    JpaRepository<User, Long> repo() {
//        return repo;
//    }
//}
