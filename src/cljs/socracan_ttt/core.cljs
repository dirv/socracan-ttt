(ns socracan-ttt.core
  (:require [socracan-ttt.game :as game]))

(enable-console-print!)

(def current-game
  (atom {:board game/new-game
         :message game/initial-message}))

(defn- cells []
  (array-seq (.getElementsByTagName js/document "td") 0))

(defn- message-node []
  (.getElementById js/document "message"))

(defn- set-text [node text]
  (set! node.textContent text))

(defn- choose-mark [move-number]
  (if (even? move-number) "X" "O"))

(defn- draw-mark [move-number cell]
  (set-text (nth (cells) cell) (choose-mark move-number)))

(defn- redraw-board []
  (doall (map-indexed draw-mark (:board @current-game))))

(defn- redraw-message []
  (set-text (message-node) (:message @current-game)))

(defn- redraw []
  (redraw-board)
  (redraw-message))

(defn- play-move [index]
  (swap! current-game
         #(game/play-move (:board %) index))
  (redraw))

(defn- attach-listener [index cell]
  (.addEventListener cell "mousedown" #(play-move index)))

(defn- on-load []
  (doall (map-indexed attach-listener (cells)))
  (redraw))

(.addEventListener js/window "onload" on-load)

