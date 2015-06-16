#include <stdio.h>

int compute_fst(const char* token)
{
    int pos=0;
    int total=0;

NODE_0 :
    pos++;
    switch (token[pos-1]) {
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
    pos++;
    switch (token[pos-1]) {
    case 'O':
        goto NODE_2;
    default:
        return -1;
    }

NODE_2 :
    pos++;
    switch (token[pos-1]) {
    case 'T':
        total += 1;
        goto NODE_3;
    case 'P':
        goto NODE_9;
    default:
        return -1;
    }

NODE_3 :
    pos++;
    switch (token[pos-1]) {
    case 'H':
        goto NODE_9;
    default:
        return -1;
    }

NODE_4 :
    pos++;
    switch (token[pos-1]) {
    case 'O':
        goto NODE_5;
    default:
        return -1;
    }

NODE_5 :
    pos++;
    switch (token[pos-1]) {
    case 'P':
        goto NODE_9;
    default:
        return -1;
    }

NODE_6 :
    pos++;
    switch (token[pos-1]) {
    case 'T':
        goto NODE_7;
    default:
        return -1;
    }

NODE_7 :
    pos++;
    switch (token[pos-1]) {
    case 'O':
        total += 1;
        goto NODE_5;
    case 'A':
        goto NODE_8;
    default:
        return -1;
    }

NODE_8 :
    pos++;
    switch (token[pos-1]) {
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
