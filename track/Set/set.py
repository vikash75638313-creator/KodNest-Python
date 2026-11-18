# ============================================================
# PYTHON SET - COMPLETE NOTES IN ONE PROGRAM
# ============================================================

# 1. SET CREATION
# ------------------------------------------------------------
# Set is unordered, mutable, and stores unique values.
# Duplicate values are automatically removed.

numbers = {10, 20, 30, 40, 20, 10}
print("Original Set:", numbers)

# Empty set
empty_set = set()
print("Empty Set:", empty_set)

# {} creates an empty dictionary, not an empty set
empty_dictionary = {}
print("Empty Dictionary:", empty_dictionary)


# ============================================================
# 2. SET PROPERTIES AND BUILT-IN FUNCTIONS
# ============================================================

print("\n--- Set Properties ---")

print("Type:", type(numbers))
print("Length:", len(numbers))
print("Maximum:", max(numbers))
print("Minimum:", min(numbers))
print("Sum:", sum(numbers))
print("Sorted:", sorted(numbers))       # Returns a list
print("Any:", any(numbers))
print("All:", all(numbers))
print("20 exists:", 20 in numbers)
print("100 does not exist:", 100 not in numbers)


# ============================================================
# 3. ADDING ELEMENTS
# ============================================================

print("\n--- Adding Elements ---")

numbers.add(50)                         # Add one element
print("After add():", numbers)

numbers.update([60, 70, 80])             # Add multiple elements
print("After update(list):", numbers)

numbers.update((90, 100))                # Add tuple elements
print("After update(tuple):", numbers)

numbers.update({110, 120})               # Add set elements
print("After update(set):", numbers)

# update() can accept multiple collections
numbers.update([130, 140], (150, 160))
print("After multiple update:", numbers)


# ============================================================
# 4. REMOVING ELEMENTS
# ============================================================

print("\n--- Removing Elements ---")

numbers.remove(20)                      # Error if element absent
print("After remove():", numbers)

numbers.discard(30)                     # No error if absent
print("After discard():", numbers)

removed_element = numbers.pop()          # Removes arbitrary element
print("Removed element using pop():", removed_element)
print("After pop():", numbers)

numbers_copy = numbers.copy()            # Creates a copy
print("Copied Set:", numbers_copy)

numbers_copy.clear()                     # Removes all elements
print("After clear():", numbers_copy)


# ============================================================
# 5. COPY METHOD
# ============================================================

print("\n--- copy() ---")

original = {10, 20, 30}
copied_set = original.copy()

copied_set.add(40)

print("Original Set:", original)
print("Copied Set:", copied_set)


# ============================================================
# 6. SET LOOPING
# ============================================================

print("\n--- Looping Through a Set ---")

skills = {"Python", "Java", "SQL", "HTML"}

for skill in skills:
    print(skill)

# Set order is not guaranteed.


# ============================================================
# 7. UNION
# ============================================================

print("\n--- Union ---")

python_students = {"Aarav", "Vikash", "Rahul", "Aman"}
java_students = {"Vikash", "Rahul", "Priya", "Rohan"}
sql_students = {"Aman", "Priya", "Neha"}

# union() combines all elements
union_result = python_students.union(java_students)
print("Union:", union_result)

# Union with multiple sets
all_students = python_students.union(java_students, sql_students)
print("Union of three sets:", all_students)

# Union shortcut operator |
print("Union using |:", python_students | java_students)

# Union assignment shortcut |=
union_copy = python_students.copy()
union_copy |= java_students
print("After |=:", union_copy)

# update() performs union and modifies original set
update_copy = python_students.copy()
update_copy.update(java_students)
print("After update():", update_copy)


# ============================================================
# 8. INTERSECTION
# ============================================================

print("\n--- Intersection ---")

# Finds common elements
intersection_result = python_students.intersection(java_students)
print("Intersection:", intersection_result)

# Intersection with multiple sets
common_all = python_students.intersection(java_students, sql_students)
print("Common in all three:", common_all)

# Shortcut operator &
print("Intersection using &:", python_students & java_students)

# Intersection assignment shortcut &=
intersection_copy = python_students.copy()
intersection_copy &= java_students
print("After &=:", intersection_copy)

# intersection_update() modifies the original set
intersection_update_copy = python_students.copy()
intersection_update_copy.intersection_update(java_students)
print("After intersection_update():", intersection_update_copy)


# ============================================================
# 9. DIFFERENCE
# ============================================================

print("\n--- Difference ---")

# Elements in Python set but not in Java set
difference_result = python_students.difference(java_students)
print("Difference:", difference_result)

# Difference with multiple sets
difference_multiple = python_students.difference(java_students, sql_students)
print("Difference with multiple sets:", difference_multiple)

# Shortcut operator -
print("Difference using -:", python_students - java_students)

# Difference assignment shortcut -=
difference_copy = python_students.copy()
difference_copy -= java_students
print("After -=:", difference_copy)

# difference_update() modifies the original set
difference_update_copy = python_students.copy()
difference_update_copy.difference_update(java_students)
print("After difference_update():", difference_update_copy)


# ============================================================
# 10. SYMMETRIC DIFFERENCE
# ============================================================

print("\n--- Symmetric Difference ---")

# Elements that are not common in both sets
symmetric_result = python_students.symmetric_difference(java_students)
print("Symmetric Difference:", symmetric_result)

# Shortcut operator ^
print("Symmetric Difference using ^:",
      python_students ^ java_students)

# Shortcut assignment operator ^=
symmetric_copy = python_students.copy()
symmetric_copy ^= java_students
print("After ^=:", symmetric_copy)

# symmetric_difference_update() modifies the original set
symmetric_update_copy = python_students.copy()
symmetric_update_copy.symmetric_difference_update(java_students)
print("After symmetric_difference_update():",
      symmetric_update_copy)


# ============================================================
# 11. isdisjoint()
# ============================================================

print("\n--- isdisjoint() ---")

set_a = {1, 2, 3}
set_b = {4, 5, 6}
set_c = {3, 4, 5}

# True if there are no common elements
print("A and B are disjoint:", set_a.isdisjoint(set_b))

# False because both sets contain 3
print("A and C are disjoint:", set_a.isdisjoint(set_c))


# ============================================================
# 12. SUBSET
# ============================================================

print("\n--- Subset ---")

small_set = {1, 2, 3}
large_set = {1, 2, 3, 4, 5}

# issubset()
print("Subset:", small_set.issubset(large_set))

# <= means subset (equal sets are also allowed)
print("Using <=:", small_set <= large_set)

# < means proper subset (must be smaller, not equal)
print("Using <:", small_set < large_set)

# Equal sets
set_x = {1, 2, 3}
set_y = {3, 2, 1}

print("X <= Y:", set_x <= set_y)         # True
print("X < Y:", set_x < set_y)           # False


# ============================================================
# 13. SUPERSET
# ============================================================

print("\n--- Superset ---")

# issuperset()
print("Superset:", large_set.issuperset(small_set))

# >= means superset (equal sets are allowed)
print("Using >=:", large_set >= small_set)

# > means proper superset (must contain more elements)
print("Using >:", large_set > small_set)

# Equal sets
print("X >= Y:", set_x >= set_y)         # True
print("X > Y:", set_x > set_y)           # False


# ============================================================
# 14. SET COMPARISON
# ============================================================

print("\n--- Set Comparison ---")

set1 = {1, 2, 3}
set2 = {1, 2, 3}
set3 = {1, 2, 3, 4}

print("set1 == set2:", set1 == set2)
print("set1 != set3:", set1 != set3)

print("set1 <= set3:", set1 <= set3)
print("set1 < set3:", set1 < set3)

print("set3 >= set1:", set3 >= set1)
print("set3 > set1:", set3 > set1)


# ============================================================
# 15. SET COMPREHENSION
# ============================================================

print("\n--- Set Comprehension ---")

data = [1, 2, 2, 3, 4, 4, 5, 6, 7, 8]

# Create a set
unique_values = {i for i in data}
print("Unique Values:", unique_values)

# Even numbers
even_numbers = {i for i in data if i % 2 == 0}
print("Even Numbers:", even_numbers)

# Odd numbers
odd_numbers = {i for i in data if i % 2 != 0}
print("Odd Numbers:", odd_numbers)

# Squares
squares = {i ** 2 for i in range(1, 6)}
print("Squares:", squares)

# Numbers greater than 5
greater_than_five = {i for i in data if i > 5}
print("Greater than 5:", greater_than_five)


# ============================================================
# 16. TYPE CONVERSION
# ============================================================

print("\n--- Type Conversion ---")

list_data = [10, 20, 20, 30, 40, 40]
set_from_list = set(list_data)
print("List to Set:", set_from_list)

tuple_data = (1, 2, 2, 3, 4, 4)
set_from_tuple = set(tuple_data)
print("Tuple to Set:", set_from_tuple)

string_data = "programming"
set_from_string = set(string_data)
print("String to Set:", set_from_string)


# ============================================================
# 17. REMOVE DUPLICATES FROM LIST
# ============================================================

print("\n--- Remove Duplicates ---")

duplicate_list = [10, 20, 10, 30, 20, 40, 50]

# Using set
unique_list = list(set(duplicate_list))
print("Original List:", duplicate_list)
print("Unique List:", unique_list)

# Maintaining original order using dict
ordered_unique = list(dict.fromkeys(duplicate_list))
print("Unique List with Order:", ordered_unique)


# ============================================================
# 18. FROZENSET
# ============================================================

print("\n--- Frozenset ---")

frozen_numbers = frozenset([10, 20, 30, 20, 40])

print("Frozenset:", frozen_numbers)
print("Type:", type(frozen_numbers))

# Frozenset is immutable.
# You cannot use:
# frozen_numbers.add(50)
# frozen_numbers.remove(10)
# frozen_numbers.clear()


# ============================================================
# 19. SET OF FROZENSETS
# ============================================================

print("\n--- Set of Frozensets ---")

set1 = frozenset([1, 2, 3])
set2 = frozenset([4, 5, 6])

nested_set = {set1, set2}
print("Set of Frozensets:", nested_set)


# ============================================================
# 20. SET OPERATORS QUICK REVISION
# ============================================================

print("\n--- Operators Quick Revision ---")

a = {1, 2, 3, 4}
b = {3, 4, 5, 6}

print("Union | :", a | b)
print("Intersection & :", a & b)
print("Difference - :", a - b)
print("Symmetric Difference ^ :", a ^ b)

print("Subset <= :", a <= b)
print("Proper Subset < :", a < b)

print("Superset >= :", a >= b)
print("Proper Superset > :", a > b)

# Assignment operators work on the set and modify it:
#
# a |= b   Union update
# a &= b   Intersection update
# a -= b   Difference update
# a ^= b   Symmetric difference update


# ============================================================
# 21. ALL SET METHODS - QUICK REVISION
# ============================================================

# add()                       -> Add one element
# clear()                     -> Remove all elements
# copy()                      -> Copy the set
# difference()                 -> Find elements only in first set
# difference_update()          -> Remove common elements from first set
# discard()                   -> Remove element safely
# intersection()              -> Find common elements
# intersection_update()       -> Keep only common elements
# isdisjoint()               -> Check whether sets have no common elements
# issubset()                 -> Check whether set is a subset
# issuperset()               -> Check whether set is a superset
# pop()                       -> Remove an arbitrary element
# remove()                    -> Remove specified element
# symmetric_difference()      -> Find non-common elements
# symmetric_difference_update()-> Update set with non-common elements
# union()                     -> Combine sets
# update()                    -> Add multiple elements


# ============================================================
# 22. IMPORTANT SET RULES
# ============================================================

# 1. Set stores only unique values.
# 2. Set is unordered.
# 3. Set is mutable.
# 4. Set does not support indexing.
# 5. Set does not support slicing.
# 6. Empty set is created using set().
# 7. {} creates an empty dictionary.
# 8. Set elements must be hashable.
# 9. List and dictionary cannot be stored inside a set.
# 10. A tuple can be stored if all its elements are hashable.
# 11. sorted(set) returns a list.
# 12. pop() removes an arbitrary element.
# 13. remove() raises KeyError if the element does not exist.
# 14. discard() does not raise an error if the element is absent.
# 15. Set operations are useful for removing duplicates and
#     finding common or different elements.


# ============================================================
# END OF PYTHON SET COMPLETE NOTES
# ============================================================