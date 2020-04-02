import java.util.Scanner;
class FA
{
    static final int S = 0, D = 1, E = 2, J = 3, X = 4, INVALID = -1;
    static int state = S;

    // Function for the starting state (S)
    static void start(char c)
    {
        if (c == 'a')
        {
            state = D;
        }
        // -1 is used to check for any invalid symbol
        else
        {
            state = INVALID;
        }
    }

    // Function for the starting state (D)
    static void transition1(char c)
    {
        if (c == 'a' || c == 'd')
        {
            state = E;
        }
        else if (c == 'b')
        {
            state = J;
        }
        else
        {
            state = -1;
        }
    }

    // Function for the starting state (E)
    static void transition2(char c)
    {
        if (c == 'a')
        {
            state = E;
        }
        else if (c == 'e')
        {
            state = X;
        }
        else
        {
            state = -1;
        }
    }

    // Function for the starting state (J)
    static void transition3(char c)
    {
        if (c == 'c')
        {
            state = S;
        }
        else
        {
            state = -1;
        }
    }

    // Function for the starting state (X)
    static void transition4(char c)
    {
        state = -1;
    }

    static int isAccepted(char str[])
    {
        int i, len = str.length;

        for (i = 0; i < len; i++)
        {
            if (state == S)
                start(str[i]);

            else if (state == D)
                transition1(str[i]);

            else if (state == E)
                transition2(str[i]);

            else if (state == J)
                transition3(str[i]);

            else if (state == X)
                transition4(str[i]);
            else
                return 0;
        }

        if (state == E)
        {
            return 0;
        }
        else
            return 1;
    }


    public static void main(String []args)
    {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        char str[] = expression.toCharArray();

        if (isAccepted(str) == 1)
            System.out.printf("ACCEPTED");
        else
            System.out.printf("NOT ACCEPTED");
    }
}

