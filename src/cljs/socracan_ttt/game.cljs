(ns socracan-ttt.game)

(def new-game [])
(def initial-message "X's go")

(defn play-move [board index]
  {:message "TODO"
   :board (conj board index)})
