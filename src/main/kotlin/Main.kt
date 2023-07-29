    var option:Int =-1
    var numRow: Int = 0
    var numSeat: Int = 0
    var capacity: Int = 0
    var cinema : MutableList<MutableList<Char>> =  ArrayList()
    var ticket: Int = 0
    var purchasedTickets: Int =0
    var soldTicketsPercentage: String = ""
    var totalIncome: Int = 0
    var currentIncome: Int = 0


    fun setPercentage(capacity: Int, sold: Int) {
        var total = (sold.toFloat() / capacity) * 100
        soldTicketsPercentage = "%.2f".format(total)
    }

    fun buildCinema():Unit {

        do {
            println("Enter the number of rows:")
            numRow = readln().toInt()
            if(numRow<1 || numRow>9){
                print("Please enter a valid row number!")
            }
        } while (numRow<1 || numRow>9)

        do {
            println("Enter the number of seats in each row:")
            numSeat = readln().toInt()
            if(numSeat<1 || numSeat>9){
                print("Please enter a valid seat number!")
            }
        } while (numSeat < 1 || numSeat>9)



        capacity = numSeat*numRow
        setTotalIncome(capacity, numRow, numSeat)
        cinema = MutableList(numRow+1) { MutableList(numSeat) { 'S' } }
    }

    fun showTheSeats(numSeat:Int,numRow:Int ,cinema:MutableList<MutableList<Char>>):Unit{
        val rowSeatQuantity = mutableListOf(0)

        for (i: Int in 1..numSeat) {
            rowSeatQuantity.add(i)
        }

        println("Cinema:")
        print("   ")
        println(rowSeatQuantity.drop(1).joinToString(" "))

        for (k: Int in 1..numRow) {
            print(" ")
            print(k)
            print(" ")
            println(cinema[k].joinToString(" "))
        }
    }

    fun ticketPricing(capacity: Int, rowNum: Int, numRow: Int): Unit {

        ticket = if (capacity < 59) {
            10
        } else {
            val frontHalf = (numRow / 2)
            if (rowNum <= frontHalf) {
                10
            } else {
                8
            }
        }

        print("Ticket price: $${ticket}")
    }

    fun setTotalIncome (capacity: Int, numRow: Int, numSeat: Int){
        val backHalf = (numRow/2) * numSeat
        val frontHalf = capacity - backHalf
        var price = (frontHalf*8) + (backHalf*10)
        totalIncome = if (capacity<59) {
            capacity*10
        } else{
           price
        }
    }

    fun showStatistics(){
        setPercentage(capacity, purchasedTickets)

        print("Number of purchased tickets: ${purchasedTickets}\n"+
                "Percentage: ${soldTicketsPercentage}%\n"+
                "Current income:$${currentIncome}\n"+
                "Total income: $${totalIncome}\n")

    }

    //funcion comprar ticket
    fun buyATicket(cinema:MutableList<MutableList<Char>>, capacity:Int, numRow:Int) {

        var control: Int = 0
        var rowNum:Int = 1
        var seatNum: Int = 1

            do {
            println("Enter a row number:")
            rowNum = readln().toInt()
            println("Enter a seat number in that row:")
             seatNum = readln().toInt()

            if(rowNum>numRow || seatNum>numSeat){
                println("Wrong input!")
            } else {
                if( cinema[rowNum][seatNum-1]=='B'){
                    println("That ticket has already been purchased!")

                } else {

                    control = 1
                }
            }

        } while (control==0)

                ticketPricing(capacity, rowNum, numRow)

                currentIncome = currentIncome.plus(ticket)

                cinema[rowNum][seatNum-1]='B'

                purchasedTickets++
    }

    fun main() {
        buildCinema()
        do {
            print("\n\n1. Show the seats\n"+
                    "2. Buy a ticket\n"+
                    "3. Statistics\n"+
                    "0. Exit\n")
            option=readln().toInt()

            when(option){
                1 -> showTheSeats(numSeat,numRow, cinema)
                2 -> buyATicket(cinema,capacity, numRow)
                3 -> showStatistics()
            }
        } while (option != 0)
    }