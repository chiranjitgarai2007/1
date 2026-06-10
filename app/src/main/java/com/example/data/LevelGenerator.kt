package com.example.data

object LevelGenerator {
    fun generateLevels(): List<LevelEntity> {
        val levels = mutableListOf<LevelEntity>()
        var id = 1
        
        // Chapter 1: Number System (1-10)
        levels.add(LevelEntity(id++, 1, "What is the smallest natural number?", "Starts from 1", "1", "Natural numbers start from 1, 2, 3..."))
        levels.add(LevelEntity(id++, 1, "Which whole number is not a natural number?", "The number representing nothing", "0", "0 is a whole number but not a natural number."))
        levels.add(LevelEntity(id++, 1, "-5 is what type of number?", "It is negative", "Integer", "Integers include positive, negative numbers and zero.", isUnlocked = false))
        levels.add(LevelEntity(id++, 1, "What is the next even number after 14?", "Add 2", "16", "Even numbers are divisible by 2. 14 + 2 = 16."))
        levels.add(LevelEntity(id++, 1, "What is the next odd number after 21?", "Add 2", "23", "Odd numbers are not divisible by 2. 21 + 2 = 23."))
        levels.add(LevelEntity(id++, 1, "Is 0 a positive integer?", "Neither positive nor negative", "False", "0 is neither positive nor negative."))
        levels.add(LevelEntity(id++, 1, "Find the sum of the first 3 natural numbers.", "1+2+3", "6", "1 + 2 + 3 = 6."))
        levels.add(LevelEntity(id++, 1, "Is pi (π) rational or irrational?", "Cannot be expressed as p/q", "Irrational", "Pi cannot be written as a simple fraction."))
        levels.add(LevelEntity(id++, 1, "Solve: 5 - (-3) = ?", "Minus minus is plus", "8", "5 + 3 = 8."))
        levels.add(LevelEntity(id++, 1, "What is the absolute value of -10?", "Distance from zero", "10", "Absolute value is always positive."))

        // Chapter 2: Basic Arithmetic (11-20)
        levels.add(LevelEntity(id++, 2, "25 + 47 = ?", "Add the units, then tens", "72", "25 + 47 = 72."))
        levels.add(LevelEntity(id++, 2, "104 - 38 = ?", "Borrow from hundreds", "66", "104 - 38 = 66."))
        levels.add(LevelEntity(id++, 2, "12 × 8 = ?", "10×8 + 2×8", "96", "12 × 8 = 96."))
        levels.add(LevelEntity(id++, 2, "144 ÷ 12 = ?", "What times 12 is 144?", "12", "12 × 12 = 144."))
        levels.add(LevelEntity(id++, 2, "Solve using BODMAS: 5 + 3 × 4", "Multiply first", "17", "3×4 = 12, then 5+12 = 17."))
        levels.add(LevelEntity(id++, 2, "Solve: (10 + 5) ÷ 3", "Brackets first", "5", "15 ÷ 3 = 5."))
        levels.add(LevelEntity(id++, 2, "15 × 15 = ?", "Square of 15", "225", "15 × 15 = 225."))
        levels.add(LevelEntity(id++, 2, "What is half of 250?", "Divide by 2", "125", "250 ÷ 2 = 125."))
        levels.add(LevelEntity(id++, 2, "Solve: 20 - 4 × 2 + 6", "Multiply first, then from left to right", "18", "4×2=8, 20-8=12, 12+6=18."))
        levels.add(LevelEntity(id++, 2, "If you have 3 boxes of 12 apples each, how many apples?", "Multiply", "36", "3 × 12 = 36."))

        // Chapter 3: Fractions & Decimals (21-30)
        levels.add(LevelEntity(id++, 3, "Convert 1/2 to a decimal.", "Divide 1 by 2", "0.5", "1 ÷ 2 = 0.5"))
        levels.add(LevelEntity(id++, 3, "What is 25% of 200?", "Quarter of 200", "50", "200 × 0.25 = 50"))
        levels.add(LevelEntity(id++, 3, "Add: 1/4 + 1/4 = ? (Use decimal)", "Common denominator is 4", "0.5", "2/4 = 1/2 = 0.5"))
        levels.add(LevelEntity(id++, 3, "Convert 0.75 to a percentage.", "Multiply by 100", "75", "0.75 × 100 = 75%"))
        levels.add(LevelEntity(id++, 3, "Simplify the ratio 10:25", "Divide both by 5", "2:5", "10/5 = 2, 25/5 = 5."))
        levels.add(LevelEntity(id++, 3, "Write 3/5 as a decimal.", "Multiply numerator/denominator by 2", "0.6", "6/10 = 0.6"))
        levels.add(LevelEntity(id++, 3, "If a toy costs $40 and has a 10% discount, what is the discount amount?", "Find 10% of 40", "4", "40 × 0.10 = 4."))
        levels.add(LevelEntity(id++, 3, "If a ratio is 1:3 and the total is 40, what is the smaller part?", "Divide total by sum of ratio parts", "10", "1+3=4 parts. 40/4 = 10 per part. 1×10 = 10."))
        levels.add(LevelEntity(id++, 3, "Calculate: 1.5 + 2.3", "Add decimals", "3.8", "1.5 + 2.3 = 3.8"))
        levels.add(LevelEntity(id++, 3, "Calculate: 5.0 - 1.2", "Borrow from unit", "3.8", "5.0 - 1.2 = 3.8"))

        // Chapter 4: Average & Number Theory (31-40)
        levels.add(LevelEntity(id++, 4, "What is the average of 2, 4, and 6?", "Sum / Count", "4", "(2+4+6)/3 = 12/3 = 4"))
        levels.add(LevelEntity(id++, 4, "Find the LCM of 4 and 6.", "Smallest multiple common to both", "12", "Multiples of 4: 4, 8, 12. Multiples of 6: 6, 12."))
        levels.add(LevelEntity(id++, 4, "Find the HCF (GCD) of 12 and 18.", "Largest factor common to both", "6", "Factors of 12: 1,2,3,4,6,12. Factors of 18: 1,2,3,6,9,18."))
        levels.add(LevelEntity(id++, 4, "Average of first 5 natural numbers?", "(1+2+3+4+5)/5", "3", "15/5 = 3."))
        levels.add(LevelEntity(id++, 4, "Is 17 a prime number?", "Only divisible by 1 and itself", "True", "17 has only two factors: 1 and 17."))
        levels.add(LevelEntity(id++, 4, "LCM of 5 and 7?", "Since both are prime, multiply them", "35", "5 × 7 = 35."))
        levels.add(LevelEntity(id++, 4, "HCF of 20 and 30?", "Largest number dividing both", "10", "Both are divisible by 10."))
        levels.add(LevelEntity(id++, 4, "If the average of two numbers is 10, what is their sum?", "Average × Count", "20", "10 × 2 = 20."))
        levels.add(LevelEntity(id++, 4, "Number sequence: 3, 6, 9, ?", "Add 3", "12", "Pattern is +3."))
        levels.add(LevelEntity(id++, 4, "Number sequence: 4, 8, 16, ?", "Multiply by 2", "32", "Each number is double the previous."))

        // Chapter 5: Commercial Mathematics (41-50)
        levels.add(LevelEntity(id++, 5, "Cost=100, Selling Price=120. Profit?", "SP - CP", "20", "120 - 100 = 20."))
        levels.add(LevelEntity(id++, 5, "Cost=50, Selling Price=40. Loss?", "CP - SP", "10", "50 - 40 = 10."))
        levels.add(LevelEntity(id++, 5, "Principal=1000, Rate=5%, Time=1 yr. Simple Interest?", "(P*R*T)/100", "50", "(1000*5*1)/100 = 50."))
        levels.add(LevelEntity(id++, 5, "P=200, R=10%, T=2. Simple Interest?", "(P*R*T)/100", "40", "(200*10*2)/100 = 40."))
        levels.add(LevelEntity(id++, 5, "Cost=200, Profit=10%. Selling Price?", "CP + (CP*Profit%)", "220", "10% of 200 is 20. 200 + 20 = 220."))
        levels.add(LevelEntity(id++, 5, "Cost=100, Loss=20%. Selling Price?", "CP - (CP*Loss%)", "80", "100 - 20 = 80."))
        levels.add(LevelEntity(id++, 5, "Total amount if P=500 and Interest=50?", "P + I", "550", "500 + 50 = 550."))
        levels.add(LevelEntity(id++, 5, "Profit percentage if CP=100, Profit=25?", "(Profit/CP)*100", "25", "(25/100)*100 = 25%."))
        levels.add(LevelEntity(id++, 5, "Selling Price = 150, Profit = 50. Cost Price?", "SP - Profit", "100", "150 - 50 = 100."))
        levels.add(LevelEntity(id++, 5, "Compound interest on 100 at 10% for 2 years. Amount?", "P(1+R/100)^t", "121", "100(1.10)^2 = 121."))

        // Chapter 6: Time Mathematics (51-60)
        levels.add(LevelEntity(id++, 6, "Distance=100 km, Time=2 hrs. Speed?", "Speed = Distance/Time", "50", "100/2 = 50 km/hr."))
        levels.add(LevelEntity(id++, 6, "Speed=60 km/hr, Time=3 hrs. Distance?", "Distance = Speed * Time", "180", "60 * 3 = 180 km."))
        levels.add(LevelEntity(id++, 6, "A does work in 10 days, B in 10 days. Together?", "1/A + 1/B = 1/T", "5", "1/10 + 1/10 = 2/10 = 1/5. T=5 days."))
        levels.add(LevelEntity(id++, 6, "Convert 2 hours to minutes.", "Multiply by 60", "120", "2 * 60 = 120."))
        levels.add(LevelEntity(id++, 6, "Distance=200, Speed=50. Time?", "Time = Distance / Speed", "4", "200/50 = 4 hrs."))
        levels.add(LevelEntity(id++, 6, "A train is 100m long, passes a pole in 10s. Speed in m/s?", "Distance / Time", "10", "100 / 10 = 10 m/s."))
        levels.add(LevelEntity(id++, 6, "If 5 workers complete a task in 4 days, how long for 1 worker?", "Inverse proportion", "20", "5 * 4 = 20 days."))
        levels.add(LevelEntity(id++, 6, "1 hour 30 mins = ? minutes", "60 + 30", "90", "1 hr = 60 mins. 60+30 = 90."))
        levels.add(LevelEntity(id++, 6, "A runs 5m/s. How far in 10s?", "Speed × Time", "50", "5 × 10 = 50m."))
        levels.add(LevelEntity(id++, 6, "If I travel at 80km/h relative to another train at 20km/h. Speed of combination?", "Depends on direction", "100", "Opposite direction sum: 80+20=100."))

        // Chapter 7: Algebra (61-75)
        levels.add(LevelEntity(id++, 7, "Solve for x: x + 5 = 12", "Subtract 5", "7", "12 - 5 = 7."))
        levels.add(LevelEntity(id++, 7, "Solve for y: 3y = 21", "Divide by 3", "7", "21 / 3 = 7."))
        levels.add(LevelEntity(id++, 7, "Simplify: 2a + 3a", "Add coefficients", "5a", "2+3 = 5, so 5a."))
        levels.add(LevelEntity(id++, 7, "Solve: 2x - 4 = 6", "Add 4, divide by 2", "5", "2x = 10 -> x = 5."))
        levels.add(LevelEntity(id++, 7, "Value of x^2 if x=4", "4 squared", "16", "4 * 4 = 16."))
        levels.add(LevelEntity(id++, 7, "If a=2, b=3, evaluate: ab + a", "Multiply then add", "8", "(2*3) + 2 = 6+2 = 8."))
        levels.add(LevelEntity(id++, 7, "Expand: 2(x + 3)", "Multiply both terms by 2", "2x+6", "2*x + 2*3 = 2x+6."))
        levels.add(LevelEntity(id++, 7, "Solve: x / 4 = 5", "Multiply by 4", "20", "5 * 4 = 20."))
        levels.add(LevelEntity(id++, 7, "Is '3x + 2' an expression or equation?", "Does it have an '=' sign?", "Expression", "Equations have '='."))
        levels.add(LevelEntity(id++, 7, "If 5 - x = 2, what is x?", "5 - 2", "3", "5 - 3 = 2."))
        levels.add(LevelEntity(id++, 7, "Simplify: x * x * x", "Use exponents", "x^3", "Three x's multiplied = x^3."))
        levels.add(LevelEntity(id++, 7, "Root of: x^2 = 25 (Positive)", "What times itself is 25?", "5", "5 * 5 = 25."))
        levels.add(LevelEntity(id++, 7, "Solve for m: 4m + m = 25", "Combine terms", "5", "5m = 25 -> m = 5."))
        levels.add(LevelEntity(id++, 7, "Factor: 3x^2 + 6x = 3x(?)", "What remains?", "x+2", "3x(x+2) = 3x^2 + 6x."))
        levels.add(LevelEntity(id++, 7, "Evaluate x^0", "Any non-zero power to 0", "1", "Any number to power 0 is 1."))

        // Chapter 8: Geometry (76-85)
        levels.add(LevelEntity(id++, 8, "How many degrees in a circle?", "Full rotation", "360", "A circle has 360 degrees."))
        levels.add(LevelEntity(id++, 8, "Sum of angles in a triangle?", "Half of a square", "180", "All triangles sum to 180 degrees."))
        levels.add(LevelEntity(id++, 8, "Angle greater than 90 but less than 180?", "Ob...", "Obtuse", "An obtuse angle is >90° and <180°."))
        levels.add(LevelEntity(id++, 8, "How many sides does a hexagon have?", "Hex = Six", "6", "A hexagon has 6 sides."))
        levels.add(LevelEntity(id++, 8, "Angle exactly 90 degrees?", "Right", "Right", "A right angle is exactly 90°."))
        levels.add(LevelEntity(id++, 8, "Longest side of a right-angled triangle?", "Hypo...", "Hypotenuse", "The side opposite the right angle."))
        levels.add(LevelEntity(id++, 8, "Sum of interior angles of quadrilateral?", "Two triangles", "360", "180 * 2 = 360."))
        levels.add(LevelEntity(id++, 8, "A polygon with 5 sides.", "Penta...", "Pentagon", "A pentagon has 5 sides."))
        levels.add(LevelEntity(id++, 8, "Are all sides of a rhombus equal?", "Yes or No", "True", "All 4 sides of a rhombus are equal length."))
        levels.add(LevelEntity(id++, 8, "Line touching circle at one point?", "Tan...", "Tangent", "A tangent line touches a curve at one point."))

        // Chapter 9: Mensuration (86-92)
        levels.add(LevelEntity(id++, 9, "Area of rectangle: Length=5, Width=4", "L * W", "20", "5 * 4 = 20."))
        levels.add(LevelEntity(id++, 9, "Perimeter of square with side 6?", "4 * side", "24", "4 * 6 = 24."))
        levels.add(LevelEntity(id++, 9, "Area of a triangle: Base=10, Height=4", "(1/2) * B * H", "20", "0.5 * 10 * 4 = 20."))
        levels.add(LevelEntity(id++, 9, "Volume of a cube with side 3?", "s^3", "27", "3 * 3 * 3 = 27."))
        levels.add(LevelEntity(id++, 9, "Circumference of circle formula?", "2*pi*r", "2πr", "C = 2πr or πd."))
        levels.add(LevelEntity(id++, 9, "Perimeter of rectangle: L=5, W=3", "2(L+W)", "16", "2*(5+3) = 16."))
        levels.add(LevelEntity(id++, 9, "Area of a square with perimeter 20?", "Find side first", "25", "Side = 20/4=5. Area = 5*5=25."))

        // Chapter 10: Coordinate Geometry (93-100)
        levels.add(LevelEntity(id++, 10, "In point (3, 4), what is the x-coordinate?", "First number", "3", "Coordinates are (x, y)."))
        levels.add(LevelEntity(id++, 10, "Which axis is vertical?", "y goes up", "y", "The y-axis is the vertical axis."))
        levels.add(LevelEntity(id++, 10, "Distance of point (3, 4) from origin?", "√(x^2 + y^2)", "5", "√(9+16) = √25 = 5."))
        levels.add(LevelEntity(id++, 10, "Midpoint of (0,0) and (4,6)?", "Average x and y", "(2,3)", "x: (0+4)/2=2, y: (0+6)/2=3."))
        levels.add(LevelEntity(id++, 10, "What is the origin coordinate?", "(zero, zero)", "(0,0)", "The center of the coordinate plane is (0,0)."))
        levels.add(LevelEntity(id++, 10, "Slope of horizontal line?", "Rise / Run", "0", "A horizontal line has zero vertical rise."))
        levels.add(LevelEntity(id++, 10, "In which quadrant is (-2, 3)?", "x is neg, y is pos", "2", "Quadrant 2 has -x and +y."))
        levels.add(LevelEntity(id++, 10, "Slope of points (1,2) and (2,4)?", "(y2-y1)/(x2-x1)", "2", "(4-2)/(2-1) = 2/1 = 2."))

        // Ensure exactly 100 levels. We need to add some to chapters to reach 100 total.
        // I will add 8 more logic puzzles to fill Chapter 9 & 10 to standard lengths.
        
        // Let's add them at the end. Actually the current ID is 101 after adding 100 levels. Let me check the sizes.
        // Chapter 1: 10
        // Chapter 2: 10
        // Chapter 3: 10
        // Chapter 4: 10
        // Chapter 5: 10
        // Chapter 6: 10
        // Chapter 7: 15
        // Chapter 8: 10
        // Chapter 9: 7
        // Chapter 10: 8
        // Total = 100 levels exactly!
        
        levels[0] = levels[0].copy(isUnlocked = true) // Only first level unlocked by default
        return levels
    }
}
