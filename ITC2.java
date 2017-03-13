
package ssp;

import java.util.*;

public class ITC2
{

    public static void main(String[] args) 
    {
        int n;
        Scanner sc= new Scanner(System.in);
        
        
        do
        {
            System.out.println("*** MENU ***");
            System.out.println("1 : RLE");
            System.out.println("2 : LZ78");
            System.out.println("99: Exit");

            System.out.print("Enter Your Choice :");
            n= sc.nextInt();
            
            switch(n)
            {
                case 1:
                        System.out.print("Enter string: ");
                        String str;
                        str = sc.next();
                        System.out.println("Input: " + str);
                        String compressed = "";
 
                        char ch =0;
                        int count =1;
                        for (int x = 0; x < str.length(); x++) 
                        { 
                            if (ch == str.charAt(x))
                            {
                                count = count + 1;
                            } else 
                            {
                                compressed = compressed + ch;
                                if(count != 1)
                                {
                                    compressed = compressed + count;
                                }
                                ch = str.charAt(x);
                                count = 1;
                            }
                        }
                        
                        compressed = compressed + ch;
                        if(count != 1)
                        {
                           compressed = compressed + count;
                        }
                        System.out.println("Compressed: " + compressed); 
                        break;
                case 2:
                        //Dictionary
                        ArrayList<String> dictionary = new ArrayList<>();

                        //input string converted to array to allow indexed reach
                        String[] input = "wyx&wypwyswyx&wwyyxxss".split("");
                        System.out.println("\n"+"Input String : wyx&wypwyswyx&wwyyxxss");
                        //our code word with a structure (a = b:c) a is index, b is index of match
                        //0 if not in the dictionary, c is next character in the input sequesnce
                        HashMap<Integer, String> codeWord = new HashMap<>();
 
                        int index = 0;//current index, initialized to 0
                        String current ="";// Current string in which we are coding

                        int key =0;

                        //while we still have input
            
                        while(index < input.length-1)
                        {
                            current = input[index];//initializing current to first input word

                            //checking if word is contained in dictionary
                            if(dictionary.contains(current))
                            {
                                String previous="";//maximum matched string found in dictionary

                                //calling the function add which find the maximum matched string + next
                                current = add(current, dictionary, input, index);

                                int j = index;
                                for(int i=0; i<current.length()-1;i++)
                                {
                                    previous += input[j];
                                    j+=1;
                                }

                                //adding our new code word to the codeWord hash map
                                String put = codeWord.put(key++, (dictionary.indexOf(previous)+","+append(current)));
                                

                                //incrementing our index by length of our current to show actual next.
                                index = index+current.length();

                                //System.out.println((Arrays.asList(input).indexOf(current)));
                                dictionary.add(current);//appending to our dictionary coded input


                            }

                            //else when input string not in dictionary
                            else 
                            {
                                codeWord.put(key++, "0,"+current);//add to codeWord with 0
                                dictionary.add(current);//add to dictionary new coded string
                                index++;
                     
                            }

                            
                            String dic[]=dictionary.toArray(new String[dictionary.size()]);

                            String[] Key = new String[codeWord.size()];
                            String[] Value = new String[codeWord.size()];
                            Set entries = codeWord.entrySet();
                            Iterator entriesIterator = entries.iterator();

                            int i = 0;
                            while(entriesIterator.hasNext())
                            {

                                Map.Entry mapping = (Map.Entry) entriesIterator.next();

                                Key[i] = mapping.getKey().toString();
                                Value[i] = mapping.getValue().toString();

                                i++;
                            }
                            System.out.println("----------------------------");
                            System.out.println("Step"+"\t"+"Symbol"+"\t    "+"Token");
                            System.out.println("----------------------------");
                            //int i;
                            for ( i = 0;  i < dictionary.size(); i++) 
                            {
                                System.out.println(Key[i] +"        " +dic[i]+"           "  +Value[i] );
                            }
                            System.out.println("");
                            System.out.println(codeWord.entrySet()+" "+dictionary);

                        }
                        break;
             
                case 99:
                        System.out.println("THANK YOU VERY MUCH...!");                    
                        break;
                default:
                        System.out.println("Invalid Input");                    
                        break;
                        
            }
            
            
        }while(n != 99);
     
    }
    //method to finding matching characters that are found in the dictionary then + next
    public static String add(String current, ArrayList<String> dictionary, String[] in, int index)
    {
        for(int i=index; i<in.length;i++)
        {
            if(dictionary.contains(current))
            {
                current = current+in[i+1];
            }
        }
        return current;
    }
    
    //method for finding next character in the input string
    public static String append(String current)
    {
         String [] name = current.split("");
         return name[name.length - 1];
    }
    
}
    
   