rows = int(input("Enter the number of rows: "))
for i in range(1, rows + 1):

    # print spaces
    for j in range(rows - i):
        print(" ",end="")
    # print increasing alphabet
    for j in range(1, i + 1):
        print(chr(64 + j),end="")
    # print decreasing alphabet
    for j in range(i - 1, 0, -1):
        print(chr(64 + j),end="")

    print()            