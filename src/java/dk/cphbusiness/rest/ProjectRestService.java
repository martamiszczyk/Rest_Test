/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Facade;
import dk.cphbusiness.entity.Project;
import dk.cphbusiness.entity.ProjectUser;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author sofus
 */
@Path("projects")
public class ProjectRestService
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestService
     */
    Gson gson;
    static Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ProjectRestService()
    {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    }

    @GET
    @Produces("application/json")

    public String getProjects()
    {
        JsonArray projects = new JsonArray();
        JsonObject project = new JsonObject();
        JsonArray users = new JsonArray();
        JsonObject user = new JsonObject();

        List<Project> projectss = Facade.getAllProjects();
        List<ProjectUser> userss;

        while (!projectss.isEmpty())
        {
            Project p = projectss.get(0);
            project.addProperty("ID", p.getId());
            project.addProperty("Description", p.getDescription());
            project.addProperty("ProjectName", p.getName());
            project.addProperty("Created", formatter.format(p.getCreated()));
            project.addProperty("Last Modified", formatter.format(p.getLastModified()));

            userss = p.getProjectUsers();
            while (!userss.isEmpty())
            {
                ProjectUser u = userss.get(0);
                user.addProperty("ID", u.getId());
                user.addProperty("Email", u.getEmail());
                user.addProperty("Username", u.getUserName());
                user.addProperty("Created", formatter.format(u.getCreated()));
                users.add(user);
                userss.remove(u);
            }
            project.add("users", users);
            projects.add(project);
            projectss.remove(p);
        }
        return projects.toString();

    }

//    @GET
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String createProject(Project p)
//    {
//        return gson.toJson(Facade.createProject(p));
//    }

}
