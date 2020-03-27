package com.andriivanov.trainstestapp.data

enum class TrainStations(val index: Int) {
    RosslareEuroport(1) {
        override fun toString(): String {
            return "Rosslare Europort"
        }
    },
    RosslareStrand(2){
        override fun toString(): String {
            return "Rosslare Strand"
        }
    },
    Wexford(3),
    Enniscorthy(4),
    Gorey(5),
    Arklow(6),
    Rathdrum(7),
    Wicklow(8),
    Greystones(9),
    Bray(10),
    Shankill(11)
}