#Shallow Copy
print("--------------Shallow Copy-----------------")
import copy
original_list = [[1, 2] ,[3, 4]]
s_copy = copy.copy(original_list)

original_list[1][0] = 300
print(original_list)
print(s_copy)

#Deep Copy
print("--------------Deep Copy-----------------")
import copy
original_list = [[1, 2], [3, 4]]
new_list = copy.deepcopy(original_list)
original_list[0][0] = 100
print(original_list)
print(new_list)

