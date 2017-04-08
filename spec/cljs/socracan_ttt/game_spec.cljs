(ns socracan-ttt.game-spec 
  (:require-macros [speclj.core :refer [describe it should=]])
  (:require [speclj.core]
            [socracan-ttt.core]))

(describe "play-move"
  (it "plays a new move on an empty board"
    (should= 0 1)))
