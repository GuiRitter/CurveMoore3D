# CurveMoore3D

I wanted to make a color gradient that contained all colors. I remembered I saw somewhere a line that would fill a whole cube so I thought: if this cube is the [RGB color space](https://en.wikipedia.org/wiki/RGB_color_space), every point in this line would be a color and they would all be adjacent to each other.

I searched around and found what was in my memory: the [space‐filling Moore curve](https://en.wikipedia.org/wiki/Moore_curve). Good thing it's looping, so I can make a looping gradient.

I searched for an algorithm to build one of an order that would give the necessary amount of colors, but found nothing. So I went to study the theory behind it. Unfortunately, I imagined [rewrite systems](https://en.wikipedia.org/wiki/Rewriting) would take too long for me to learn, and I wanted to do this quickly, because there are higher priority things I want to do.

Reading further, the mention of a [hypercube](https://en.wikipedia.org/wiki/Hypercube) made me think I was way more advanced than I could handle. Only after finishing this I realized a 3D hypercube is just a cube… At least I'm well versed in [Gray code](https://en.wikipedia.org/wiki/Gray_code).

So I kept thinking about it during the following days. And things started to make sense. I understood how to use the Gray code, but I had no idea what those rotations where about. I looked into order 3 examples, compared it to order 2, and couldn't understand why the building blocks of order 3 were not equal to order 2.

Then I realized: those rotations must be transformations that are applied before building the next order, because otherwise there's no way to connect the copies. They must be math I'm not familiar with, because there's no way they can be made just with rotations.

So I set to brute force it. If I don't know how to make the "rotations", then I'll just transform them in whatever way that enables every copy to connect to its neighbors. Also, instead of transforming before generating the next order, I realized I needed to have a set of "rotations" to generate the current order and a different set of "rotations" to generate the next order, so I generate both for every order.

I don't know if the end result can be called a Moore curve, but it looks like it, works for any order, and loops. All this just to make this pretty picture:

![color gradient with all (sampled) colors](image/order_3_color.png)

I'd like to study this further to see if I can make the "rotations" a function of the Gray code.

[A few words about Maven.](https://gist.github.com/GuiRitter/1834bd024756e08ab422026a7cd24605)
