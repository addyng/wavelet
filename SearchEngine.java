import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> listOfStrings = new ArrayList<String>();

    public String handleRequest(URI url) {
        // if (url.getPath().equals("/")) {
        //     return String.format("[Addy]'s Number: %d", num);
        // } else if (url.getPath().equals("/increment")) {
        //     num += 1;
        //     return String.format("Number incremented!");
        // } else {
        //     System.out.println("Path: " + url.getPath());
        //     if (url.getPath().contains("/add")) {
        //         String[] parameters = url.getQuery().split("=");
        //         if (parameters[0].equals("count")) {
        //             num += Integer.parseInt(parameters[1]);
        //             return String.format("Number increased by %s! It's now %d", parameters[1], num);
        //         }
        //     }
        //     return "404 Not Found!";
        // }

        if (url.getPath().equals("/")) {
            return listOfStrings.toString();
        } else if (url.getPath().contains("/add")) {
            String [] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                listOfStrings.add(parameters[1]);
                return listOfStrings.toString();
            }
        } else if (url.getPath().contains("/search")) {
            ArrayList<String> listOfSearch = new ArrayList<String>();
            String [] parametersSearch = url.getQuery().split("=");
            if (parametersSearch[0].equals("s")) {
                for (int i = 0; i < listOfStrings.size(); i++) {
                    if (listOfStrings.get(i).contains(parametersSearch[1])) {
                        listOfSearch.add(listOfStrings.get(i));
                    }
                }
                return listOfSearch.toString();
            }
        }
        return "";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
