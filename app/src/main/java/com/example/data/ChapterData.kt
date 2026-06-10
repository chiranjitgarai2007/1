package com.example.data

data class Chapter(
    val id: Int,
    val title: String,
    val startLevel: Int,
    val endLevel: Int,
    val description: String
)

object ChapterData {
    val chapters = listOf(
        Chapter(1, "Number System", 1, 10, "Natural, Whole, Integer, Rational & Irrational"),
        Chapter(2, "Basic Arithmetic", 11, 20, "Addition, Subtraction, Multiplication & BODMAS"),
        Chapter(3, "Fractions & Decimals", 21, 30, "Fractions, Decimals, Percentages & Ratios"),
        Chapter(4, "Average & Theory", 31, 40, "Averages, LCM, HCF & Number Sequences"),
        Chapter(5, "Commercial Math", 41, 50, "Profit/Loss & Interest"),
        Chapter(6, "Time Mathematics", 51, 60, "Time, Work, Speed & Distance"),
        Chapter(7, "Algebra", 61, 75, "Variables, Expressions & Equations"),
        Chapter(8, "Geometry", 76, 85, "Lines, Angles, Triangles & Polygons"),
        Chapter(9, "Mensuration", 86, 92, "Area, Perimeter & Volume"),
        Chapter(10, "Coordinate Geometry", 93, 100, "Coordinates, Distance & Slopes")
    )
}
