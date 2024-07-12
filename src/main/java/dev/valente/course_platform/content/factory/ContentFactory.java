package dev.valente.course_platform.content.factory;


import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.concreteContent.Course;
import dev.valente.course_platform.content.concreteContent.Mentoring;

import java.util.Date;

public class ContentFactory {

    public static Content createContent(ContentCreationRequestDTO contentRequest){


        if(contentRequest.duration() <= 30){
            return new Course(contentRequest.description(),
                    contentRequest.duration(),
                    new Date(),
                    contentRequest.url());
        } else {
            return new Mentoring(contentRequest.description(),
                    contentRequest.duration(),
                    new Date(),
                    contentRequest.url());
        }
    }
}
