#include <stdio.h>

int compute_fst(const char* token)
{
    int pos=0;
    int total=0;

NODE_0 :
    switch (token[pos++]) {
    case 'M':
        goto NODE_1;
    case 'P':
        total += 2;
        goto NODE_4;
    case 'T':
        total += 5;
        goto NODE_4;
    case 'S':
        total += 3;
        goto NODE_6;
    default:
        return -1;
    }

NODE_1 :
    switch (token[pos++]) {
    case 'O':
        goto NODE_2;
    default:
        return -1;
    }

NODE_2 :
    switch (token[pos++]) {
    case 'T':
        total += 1;
        goto NODE_3;
    case 'P':
        goto NODE_9;
    default:
        return -1;
    }

NODE_3 :
    switch (token[pos++]) {
    case 'H':
        goto NODE_9;
    default:
        return -1;
    }

NODE_4 :
    switch (token[pos++]) {
    case 'O':
        goto NODE_5;
    default:
        return -1;
    }

NODE_5 :
    switch (token[pos++]) {
    case 'P':
        goto NODE_9;
    default:
        return -1;
    }

NODE_6 :
    switch (token[pos++]) {
    case 'T':
        goto NODE_7;
    default:
        return -1;
    }

NODE_7 :
    switch (token[pos++]) {
    case 'O':
        total += 1;
        goto NODE_5;
    case 'A':
        goto NODE_8;
    default:
        return -1;
    }

NODE_8 :
    switch (token[pos++]) {
    case 'R':
        goto NODE_9;
    default:
        return -1;
    }

NODE_9 :
    goto END;

END :
    return total;
}

int main(int argc, const char *argv[])
{
    if (argc < 2)
        return 1;

    printf("%d\n", compute_fst(argv[1]));
    return 0;
}
