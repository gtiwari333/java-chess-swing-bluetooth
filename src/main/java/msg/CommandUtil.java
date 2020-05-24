package msg;


public class CommandUtil {

    //TODO: parse into a POJO instead of array

    //parse the received command separated by # into an array
    public static String[] extractMessage(String msg) {
        //regex not supported in so defined our own function
        System.err.println("*****Received :" + msg);
        int count = 0;
        char separator = '#';                        // Substring separator
        int index = 0;
        do {// Determine the number of substrings
            ++count;
            ++index;
            index = msg.indexOf(separator, index);
        } while (index != -1);
        // Extract the substring into an array
        String[] subStr = new String[count];         // Allocate for substrings
        index = 0;                                   // Substring start index
        int endIndex;                            // Substring end index
        for (int i = 0; i < count; i++) {
            endIndex = msg.indexOf(separator, index);  // Find next separator
            if (endIndex == -1) {                       // If it is not found
                subStr[i] = msg.substring(index);       // extract to the end
            } else {                                         // otherwise
                subStr[i] = msg.substring(index, endIndex);   // to end index
            }
            index = endIndex + 1;                      // Set start for next cycle
        }
        return subStr;

    }


}
