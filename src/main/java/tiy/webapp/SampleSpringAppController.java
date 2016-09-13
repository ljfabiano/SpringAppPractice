package tiy.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jfabiano on 9/12/2016.
 */
@Controller//expects we define an end point somewhere. an end point is a url you can submit to, and get something back from it
public class SampleSpringAppController {

    WebChatClient myClient = new WebChatClient();

    @RequestMapping(path = "/person-url", method = RequestMethod.GET)//this is the defining of the end point
    public String person(Model model, String name, String city, int age) {//model is passed in to the object automatically
        //These parameters have to be the same as those that will be in the url: eg. http://localhost:8080/person?name=Joe&city=JohnsCreek&age=31


        Person p = new Person(name, city, age);
        model.addAttribute("person-object", p);//what you want your view to see you add as an attribute
        return "person-view";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {//the session is like the model(a hashmap sort of...) but difference is: 1. add to model with add attribute, and add to session is set attribute. 2. your view sees the model, but not the session object 3. the model only exists for a single transaction, but the session object lives between requests from client to server
        //
        model.addAttribute("name", session.getAttribute("userName"));
        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)//post with a payload when you are submitting more structured data vs a get command
    public String login(HttpSession session, String userName) {//http verbs: get, push, put, etc, get can pass a query string eg. ?name=Alice&city=Charleston&age=30 in the url
        session.setAttribute("userName", userName);
        return "redirect:/";//Go to this other endpoint
    }
    @RequestMapping(path = "/logout", method = RequestMethod.GET)//post with a payload when you are submitting more structured data vs a get command
    public String logout(Model model, HttpSession session) {//http verbs: get, push, put, etc, get can pass a query string eg. ?name=Alice&city=Charleston&age=30 in the url
        session.removeAttribute("userName");
        return "redirect:/";//Go to this other endpoint
    }
    @RequestMapping(path = "/chat", method = RequestMethod.GET)//post with a payload when you are submitting more structured data vs a get command
    public String chat(Model model, HttpSession session, String message) {//http verbs: get, push, put, etc, get can pass a query string eg. ?name=Alice&city=Charleston&age=30 in the url

        //the name of the field in the form needs to match the name of the parameter in the new controller method
       // myClient.sendSingleMessage(session.getAttribute("message"));
        System.out.println("about to establish the conntection");
        myClient.establishConnection();
        System.out.println("connection established");
        //System.out.println(myClient.sendSingleMessage(message));
        //session.
        return "input";//Go to this other endpoint
    }
    @RequestMapping(path = "/send-message", method = RequestMethod.POST)//post with a payload when you are submitting more structured data vs a get command
    public String message(Model model, HttpSession session, String message) {//http verbs: get, push, put, etc, get can pass a query string eg. ?name=Alice&city=Charleston&age=30 in the url

        //the name of the field in the form needs to match the name of the parameter in the new controller method
        // myClient.sendSingleMessage(session.getAttribute("message"));
        //myClient.establishConnection();
        System.out.println("about to print the response from the server");
        System.out.println(myClient.sendSingleMessage(message));
        System.out.println("printed the response from the server");
        //session.
        return "redirect:/chat";//Go to this other endpoint
    }
}

