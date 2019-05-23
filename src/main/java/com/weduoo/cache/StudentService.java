package com.weduoo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

@Service
public class StudentService {

    @Cacheable("student")
    public Student getStudentByID(String id){
        try {
            System.out.println("Going to sleeping 5 secs.");
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Student(id, "hello", "V");
    }

}
