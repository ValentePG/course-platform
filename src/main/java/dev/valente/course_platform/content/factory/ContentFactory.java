package dev.valente.course_platform.content.factory;


import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.dtos.ContentCreationRequestDTO;
import dev.valente.course_platform.content.concrete_content.Course;
import dev.valente.course_platform.content.concrete_content.Mentoring;

public class ContentFactory {
    private ContentFactory() {
    }

    public static Content createContent(ContentCreationRequestDTO contentRequest){


        if(contentRequest.duration() <= 30){
            return new Course(contentRequest.description(),
                    contentRequest.duration(),
                    contentRequest.url());
        } else {
            return new Mentoring(contentRequest.description(),
                    contentRequest.duration(),
                    contentRequest.url());
        }
    }
}
