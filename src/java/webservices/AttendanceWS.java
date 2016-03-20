/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import ejb.ICourseSemesterService;
import entities.Absence;
import entities.CourseSemester;
import entities.Student;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author User
 */
@Path("attendance")
@Stateless
public class AttendanceWS {
    
    @EJB
    private ICourseSemesterService semesterService;
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getAttendanceList(@Context UriInfo uriInfo){
        String semesterName = uriInfo.getQueryParameters().getFirst("semester");
        String date = uriInfo.getQueryParameters().getFirst("date");
        CourseSemester semester = semesterService.getSemester(semesterName);
        List<Student> students = semester.getStudents();
        JsonArrayBuilder absenceList = Json.createArrayBuilder(); 
        
        for(Student student : students){
            boolean wasAbsence = false;
            for(Absence abs : student.getAbsenceList()){
                if(sdf.format(abs.getAbsenceDate()).equals(date)){
                    wasAbsence = true;
                }
            }
            JsonObject studentJson = Json.createObjectBuilder()
                    .add("firstName", student.getFirstName())
                    .add("lastName", student.getLastName())
                    .add("email", student.getEmail())
                    .add("Absence", wasAbsence)
                    .build();
            absenceList.add(studentJson);
        }
        
        JsonObject model = Json.createObjectBuilder().add("absenceList", absenceList.build()).build();
        return model;
    }
}
