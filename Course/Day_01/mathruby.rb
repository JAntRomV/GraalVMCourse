puts "Enter the first number:"
num1 = gets.chomp.to_i # gets input, removes newline, converts to integer

puts "Enter the second number:"
num2 = gets.chomp.to_i

sum = num1 + num2
puts "The sum is: #{sum}" # String interpolation