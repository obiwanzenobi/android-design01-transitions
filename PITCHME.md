### Android Design Basics - Transition API

--- 

### Dostępne mechanizmy animacji
@ul
- View animations (android.view.animation)
- Animator API (android.animation)
- Transition API
- Animated Vector Drawable 
- MotionLayout (nie omawiane tutaj)
@ulend

+++
### android.view.animation

@ul
- Najstarsze api do animacji (Od Android 1.0)
- Pozwala jedynie animować podstawowe Propercje widoku
- Wykonuje się w etapie onDraw widoku (już po measure i layout)
- Nadal wykorzystywane do window i fragment animation
- Mniej wydajne od Animator'ów (z powodu wykonania w fazie draw - brak możliwości odroczenia renderowania itd.)

@ulend

+++
### android.animation - Animator API

@ul
- Łatwiejsze w obsłudze
- Zalecane api dla mocno customowych animacji
- Dostępne od Androida 3.0 (ale sensowne od 4.0)
- W skład wchodzą ObjectAnimator, AnimatorSet, ViewPropertyAnimator, ValueAnimator
- ViePropertyAnimator pozwala na pojedyńcze invalidacje w przypadku animacji kilku propercji
- Część animatorów zaimplementowana natywnie (RenderThread)
- Dużo łatwiejsze łączenie animacji
@ulend

+++
### Animated vector drawable

@ul
- Od Android 7.1 (api 25) animowane na RenderThread
- Animator + Vector Drawable
- Dużo lepsza wydajnośc lecz mniej opcji niż w Lottie
- Dostępne funkcje: start, pause, stop
@ulend

+++
### MotionLayout
@ul
- Ciągle w alphie
- Wysokopoziomowe animacje
- Możliwość podpięcia animacji pod interakcje użytkownika (swipe, drag itd.)
@ulend

---
### Transition API - Tranzycje i ich menadżer

+++
### Czym są tranzycje?
Tranzycje skupiają sie na prostym, opisowym deklarowaniu animacji skupionych na widokach. 
Tranzycja ma za zadanie pobrać wartości początkowe i końcowe widoku (wartość pierwotna i wyjściowa) i zwrócić odpowiedni Animator, który zostanie wykorzystany do animacji widoku.

+++

kod tranzycji wejścia

+++ 

### TransitionManager
W skład Transition Api wchodzi TransitionManager, który umożliwia przeprowadzenie tranzycji pomiędzy dwoma scenami. Sceny budowane są na podstawie ViewGroup i całego drzewa dzieci i ich parametrów. Można to robić ręcznie ale łatwiej i szybciej użyć TransitionManagera
+++
kod transition managera - prosty

+++ 
### Jak to działa?
@ul
- Transition manager tworzy scene dla podanej ViewGroup'y, w której zostają zebrane parametry widoków (zgodnie z podaną tranzycją - o tym później),
-następnie ustawia "onPreDrawListener", który zostaje powiadomiony o następnej tworzonej klatce wyświetlanego UI.
- Podczas wywołania tego callbacka wartości widoku zostają zebrane ponownie do drugiej sceny.
- Następnie zostają uruchomione tranzycje pomiędzy uzyskanymi scenami
@ulend

+++ 

Kod bardziej skomplikowanego auto-transition

---
### Rodzaje tranzycji
@ul
- AutoTransition - domyślny (fade out -> changeBounds -> fade in)
- Fade, Slide, Explode
- ChngeBounds, ImageTransform, ChangeScroll
- TransitionSet
- Customowe Tranzycje
@ulend

+++
Przy użyciu TransitionSet jesteśmy w stanie osiągnąć skomplikowane animacje w bardzo opisowy i czytelny sposób

kod slidów z róznych stron i fade środka

+++
Tworzone tranzycje są uniwersalne i mogą być użyte zarówno jako enter/exit content transition w fragmencie / activity jak i podczas zmiany stanu na pojedyńczym widoku. Wszystkie tranzycje możliwe są do zdefiniowania zarówno w kodzie jak i w xml.

---
### Custom Transition
Od razu rzuca się w oczy mała ilość dostępnych domyślnie animacji. Na szczęście bardzo łatwo dopisać nowe.
Kod 


+++ 
kod wykorzystanie w xml wraz z parametrami

+++ 

Podczas tworzenia własnych tranzycji należy pamiętać, że powinny być łatwe w łączeniu i przedstawiające pojedyńczą operacje. Np. zamiast jednej tranzycji skalującej, zmieniającej alphe i kolor lepiej stworzyć je jako każda z osobna. T sama tranzycja może być użyta na wielu ekranach a także w Shared Element transition.


---
### Podsumowanie
Do zalet należy zaliczyć dobrą czytelność, możliwość przedstawienia w postaci XML, reużywalność Tranzycji i szybkość tworzenia prostych przejść.
Do prostych zastosowań wystarczy powierzchowna znajomość beginDelayedTransition.

+++
Z wad warto wymienić konieczność zrozumienia mechanizmu odpowiadającego za orkiestracje tranzycji w celu wyłapania potencjalnych błędów. 
Transition API ma problemy przy poprawnym dyrygowaniu animacji z kilku uruchomionych tranzycji na kilku zagnieżdzeniach widoków (uruchomienie przeciwstawnych animacji zadziała jednak tylko w zakresie jednego roota)

---
### Questions?

<br>

@fa[twitter gp-contact](@wojciech_warwas)

@fa[github gp-contact](@obiwanzenobi)
