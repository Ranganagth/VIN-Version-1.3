package com.example.M3.respository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import com.example.M3.model.User;

@Repository
public interface UserRepository extends CassandraRepository<User,String> {

}