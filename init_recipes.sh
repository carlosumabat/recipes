#!/usr/bin/env bash

set -e

post_recipe() {
  curl -s -X POST "$API_BASE_URL/api/recipes" \
    -H "Content-Type: application/json" \
    -d "$1"
}

post_recipe '{
  "title": "Lemon Herb Fish",
  "description": "Pan-seared fish with fresh lemon and herbs.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Season fish with salt and dill. Heat olive oil in a pan and cook fish on both sides until done. Finish with lemon juice and serve.",
  "ingredients": ["Fish", "Salt", "Dill", "Olive Oil", "Lemon"]
}'

post_recipe '{
  "title": "Creamy Mushroom Pasta",
  "description": "Pasta in a creamy mushroom sauce.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook pasta. Saute mushroom and garlic in butter. Add cream and simmer. Combine with pasta and season with salt.",
  "ingredients": ["Pasta", "Mushroom", "Garlic", "Butter", "Cream", "Salt"]
}'

post_recipe '{
  "title": "Chickpea Spinach Stew",
  "description": "Hearty chickpea stew with greens.",
  "servings": 4,
  "isVegetarian": true,
  "instructions": "Saute onion and garlic in olive oil. Add chickpeas, tomato, and spinach. Simmer and season with paprika and salt.",
  "ingredients": ["Chickpeas", "Onion", "Garlic", "Olive Oil", "Tomato", "Spinach", "Paprika", "Salt"]
}'

post_recipe '{
  "title": "Honey Glazed Carrots",
  "description": "Sweet glazed carrots with butter and honey.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Cook carrot slices in butter until tender. Add honey and a pinch of salt, cook until glazed.",
  "ingredients": ["Carrot", "Butter", "Honey", "Salt"]
}'

post_recipe '{
  "title": "Beef and Broccoli Stir Fry",
  "description": "Quick stir fry with beef and vegetables.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Cook beef in olive oil. Add broccoli and garlic. Stir in soy sauce and cook until tender.",
  "ingredients": ["Beef", "Olive Oil", "Broccoli", "Garlic", "Soy Sauce"]
}'

post_recipe '{
  "title": "Tomato Basil Soup",
  "description": "Simple tomato soup with fresh basil.",
  "servings": 4,
  "isVegetarian": true,
  "instructions": "Cook onion and garlic in olive oil. Add tomato and simmer. Blend, add basil and salt before serving.",
  "ingredients": ["Tomato", "Onion", "Garlic", "Olive Oil", "Basil", "Salt"]
}'

post_recipe '{
  "title": "Chicken Fried Rice",
  "description": "Classic fried rice with chicken and vegetables.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Cook chicken in olive oil. Add cooked rice, peas, carrot, and soy sauce. Stir fry until heated through.",
  "ingredients": ["Chicken", "Olive Oil", "Rice", "Peas", "Carrot", "Soy Sauce"]
}'

post_recipe '{
  "title": "Oat Banana Breakfast Bowl",
  "description": "Warm oats with banana and honey.",
  "servings": 1,
  "isVegetarian": true,
  "instructions": "Cook oats with milk. Top with sliced banana, honey, and almonds.",
  "ingredients": ["Oats", "Milk", "Banana", "Honey", "Almonds"]
}'

post_recipe '{
  "title": "Garlic Butter Shrimp Style Fish",
  "description": "Fish cooked with garlic butter flavors.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Melt butter in a pan with garlic. Add fish and cook until done. Season with salt and parsley.",
  "ingredients": ["Fish", "Butter", "Garlic", "Salt", "Parsley"]
}'

post_recipe '{
  "title": "Vegetable Quinoa Bowl",
  "description": "Healthy quinoa bowl with roasted vegetables.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook quinoa. Roast zucchini, bell pepper, and onion with olive oil. Combine and season with oregano and salt.",
  "ingredients": ["Quinoa", "Zucchini", "Bell Pepper", "Onion", "Olive Oil", "Oregano", "Salt"]
}'

post_recipe '{
  "title": "Pork and Apple Skillet",
  "description": "Savory pork with sweet apple slices.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Cook pork in olive oil. Add sliced apple and rosemary. Season with salt and cook until tender.",
  "ingredients": ["Pork", "Olive Oil", "Apple", "Rosemary", "Salt"]
}'

post_recipe '{
  "title": "Spinach Feta Omelette",
  "description": "Omelette with spinach and cheese.",
  "servings": 1,
  "isVegetarian": true,
  "instructions": "Beat eggs with salt. Cook spinach in butter, add eggs and cheese. Fold and serve.",
  "ingredients": ["Eggs", "Salt", "Spinach", "Butter", "Cheese"]
}'

post_recipe '{
  "title": "Lentil Vegetable Soup",
  "description": "Comforting lentil soup with vegetables.",
  "servings": 4,
  "isVegetarian": true,
  "instructions": "Cook onion and garlic in olive oil. Add lentils, carrot, celery, and tomato. Simmer until lentils are soft. Season with thyme and salt.",
  "ingredients": ["Lentils", "Onion", "Garlic", "Olive Oil", "Carrot", "Celery", "Tomato", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Coconut Mango Rice",
  "description": "Sweet rice with coconut milk and mango.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Cook rice with coconut milk. Top with diced mango and a drizzle of honey.",
  "ingredients": ["Rice", "Coconut Milk", "Mango", "Honey"]
}'

post_recipe '{
  "title": "Beef Tomato Pasta",
  "description": "Rich beef and tomato pasta dish.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Brown beef in olive oil. Add tomato and simmer. Toss with cooked pasta and season with oregano and salt.",
  "ingredients": ["Beef", "Olive Oil", "Tomato", "Pasta", "Oregano", "Salt"]
}'

post_recipe '{
  "title": "Broccoli Cheddar Bake",
  "description": "Baked broccoli with melted cheese.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Steam broccoli. Mix with cheese and cream, bake until bubbly. Season with salt.",
  "ingredients": ["Broccoli", "Cheese", "Cream", "Salt"]
}'

post_recipe '{
  "title": "Chicken Lemon Rice Soup",
  "description": "Light chicken soup with rice and lemon.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Cook chicken in water. Add rice and cook until tender. Finish with lemon juice, dill, and salt.",
  "ingredients": ["Chicken", "Rice", "Lemon", "Dill", "Salt"]
}'

post_recipe '{
  "title": "Zucchini Tomato Saute",
  "description": "Quick sauteed vegetables.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Saute zucchini and tomato in olive oil with garlic. Season with basil and salt.",
  "ingredients": ["Zucchini", "Tomato", "Olive Oil", "Garlic", "Basil", "Salt"]
}'

post_recipe '{
  "title": "Peanut Ginger Noodles",
  "description": "Savory noodles with peanut and ginger.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook pasta. Mix peanut butter style using peanuts crushed with soy sauce and ginger. Toss with pasta and finish with sesame seeds.",
  "ingredients": ["Pasta", "Peanuts", "Soy Sauce", "Ginger", "Sesame Seeds"]
}'

post_recipe '{
  "title": "Maple Roasted Sweet Carrots",
  "description": "Roasted carrots with maple syrup glaze.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Toss carrots with olive oil and maple syrup. Roast until tender and season with salt.",
  "ingredients": ["Carrot", "Olive Oil", "Maple Syrup", "Salt"]
}'

post_recipe '{
  "title": "Garlic Butter Cod",
  "description": "Oven-baked cod with garlic herb butter.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Season fish with salt and paprika. Mix melted butter with garlic and parsley. Spread over fish and bake until flaky. Finish with lemon juice.",
  "ingredients": ["Fish", "Salt", "Paprika", "Butter", "Garlic", "Parsley", "Lemon"]
}'

post_recipe '{
  "title": "Vegetable Fried Quinoa",
  "description": "Stir-fried quinoa with mixed vegetables.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook quinoa. Saute onion, carrot, peas, and bell pepper in olive oil. Add quinoa and soy sauce, stir fry briefly and serve.",
  "ingredients": ["Quinoa", "Onion", "Carrot", "Peas", "Bell Pepper", "Olive Oil", "Soy Sauce"]
}'

post_recipe '{
  "title": "Creamy Spinach Pasta",
  "description": "Pasta tossed with spinach and cream sauce.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook pasta. Saute garlic in butter, add cream and spinach. Simmer briefly, combine with pasta, and season with salt.",
  "ingredients": ["Pasta", "Garlic", "Butter", "Cream", "Spinach", "Salt"]
}'

post_recipe '{
  "title": "Beef Barley Stew",
  "description": "Slow simmered beef stew with barley.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Brown beef in olive oil. Add onion, carrot, and barley. Cover with water and simmer until tender. Season with thyme and salt.",
  "ingredients": ["Beef", "Olive Oil", "Onion", "Carrot", "Barley", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Chickpea Lemon Salad",
  "description": "Fresh chickpea salad with herbs and citrus.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Combine chickpeas, cucumber, tomato, and parsley. Dress with olive oil, lemon juice, and salt. Toss and serve chilled.",
  "ingredients": ["Chickpeas", "Cucumber", "Tomato", "Parsley", "Olive Oil", "Lemon", "Salt"]
}'

post_recipe '{
  "title": "Chicken Basil Stir Fry",
  "description": "Quick chicken stir fry with basil.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Cook chicken in olive oil. Add garlic and bell pepper. Stir in soy sauce and fresh basil, cook until done.",
  "ingredients": ["Chicken", "Olive Oil", "Garlic", "Bell Pepper", "Soy Sauce", "Basil"]
}'

post_recipe '{
  "title": "Mushroom Rice Pilaf",
  "description": "Savory rice with mushrooms and herbs.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Saute mushroom and onion in butter. Add rice and cook briefly. Add water and simmer until rice is tender. Season with thyme and salt.",
  "ingredients": ["Mushroom", "Onion", "Butter", "Rice", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Pork Ginger Skillet",
  "description": "Pan-cooked pork with ginger flavor.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Cook pork in olive oil. Add ginger and garlic, cook until fragrant. Season with soy sauce and serve hot.",
  "ingredients": ["Pork", "Olive Oil", "Ginger", "Garlic", "Soy Sauce"]
}'

post_recipe '{
  "title": "Roasted Cauliflower with Paprika",
  "description": "Oven roasted cauliflower with spices.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Toss cauliflower with olive oil, paprika, and salt. Roast until golden and tender.",
  "ingredients": ["Cauliflower", "Olive Oil", "Paprika", "Salt"]
}'

post_recipe '{
  "title": "Apple Cinnamon Oats",
  "description": "Warm oats with apple and cinnamon.",
  "servings": 1,
  "isVegetarian": true,
  "instructions": "Cook oats with milk. Stir in diced apple, cinnamon, and honey. Simmer briefly and serve warm.",
  "ingredients": ["Oats", "Milk", "Apple", "Cinnamon", "Honey"]
}'

post_recipe '{
  "title": "Beef Stuffed Bell Peppers",
  "description": "Bell peppers filled with beef and rice.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Cook beef with onion. Mix with cooked rice and tomato. Stuff into bell peppers and bake until tender.",
  "ingredients": ["Beef", "Onion", "Rice", "Tomato", "Bell Pepper"]
}'

post_recipe '{
  "title": "Spinach Lentil Curry",
  "description": "Mild lentil curry with spinach.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook lentils until soft. Saute garlic and onion in olive oil. Add spinach, lentils, chili powder, and coconut milk. Simmer and season with salt.",
  "ingredients": ["Lentils", "Garlic", "Onion", "Olive Oil", "Spinach", "Chili Powder", "Coconut Milk", "Salt"]
}'

post_recipe '{
  "title": "Chicken Tomato Bake",
  "description": "Baked chicken with tomato and herbs.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Place chicken in baking dish. Top with tomato, oregano, olive oil, and salt. Bake until cooked through.",
  "ingredients": ["Chicken", "Tomato", "Oregano", "Olive Oil", "Salt"]
}'

post_recipe '{
  "title": "Broccoli Almond Saute",
  "description": "Quick sauteed broccoli with almonds.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Saute broccoli in olive oil. Add toasted almonds and salt. Cook until tender and serve.",
  "ingredients": ["Broccoli", "Olive Oil", "Almonds", "Salt"]
}'

post_recipe '{
  "title": "Fish Tomato Stew",
  "description": "Light fish stew with tomato base.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Saute onion and garlic in olive oil. Add tomato and simmer. Add fish pieces and cook gently until done. Season with parsley and salt.",
  "ingredients": ["Fish", "Onion", "Garlic", "Olive Oil", "Tomato", "Parsley", "Salt"]
}'

post_recipe '{
  "title": "Zucchini Mushroom Saute",
  "description": "Simple sauteed vegetables.",
  "servings": 2,
  "isVegetarian": true,
  "instructions": "Cook zucchini and mushroom in olive oil with garlic. Season with thyme and salt before serving.",
  "ingredients": ["Zucchini", "Mushroom", "Olive Oil", "Garlic", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Chicken Barley Soup",
  "description": "Hearty soup with chicken and barley.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Cook chicken in water. Add barley, carrot, and celery. Simmer until tender and season with salt and parsley.",
  "ingredients": ["Chicken", "Barley", "Carrot", "Celery", "Salt", "Parsley"]
}'

post_recipe '{
  "title": "Mango Yogurt Bowl",
  "description": "Fresh mango with creamy yogurt.",
  "servings": 1,
  "isVegetarian": true,
  "instructions": "Combine yogurt with diced mango. Drizzle honey and top with walnuts before serving.",
  "ingredients": ["Yogurt", "Mango", "Honey", "Walnuts"]
}'

post_recipe '{
  "title": "Beef Rosemary Skillet",
  "description": "Simple beef skillet with rosemary.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Cook beef in olive oil. Add rosemary and garlic. Season with salt and cook to desired doneness.",
  "ingredients": ["Beef", "Olive Oil", "Rosemary", "Garlic", "Salt"]
}'

post_recipe '{
  "title": "Peas and Mint Rice",
  "description": "Fragrant rice with peas and mint.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook rice until tender. Stir in cooked peas, chopped mint, olive oil, and salt. Serve warm.",
  "ingredients": ["Rice", "Peas", "Mint", "Olive Oil", "Salt"]
}'

post_recipe '{
  "title": "Garlic Lemon Chicken Skillet",
  "isVegetarian": false,
  "instructions": "Season chicken with salt and paprika. Heat olive oil in a skillet, add garlic and cook briefly. Add chicken and cook until done. Finish with lemon juice.",
  "ingredients": ["Chicken", "Salt", "Paprika", "Olive Oil", "Garlic", "Lemon"]
}'

post_recipe '{
  "title": "Spinach Mushroom Rice",
  "isVegetarian": true,
  "instructions": "Cook rice separately. Saute mushroom and garlic in butter, add spinach and cook until wilted. Mix with rice and season with salt.",
  "ingredients": ["Rice", "Mushroom", "Garlic", "Butter", "Spinach", "Salt"]
}'

post_recipe '{
  "title": "Beef Onion Stir Fry",
  "isVegetarian": false,
  "instructions": "Cook beef in olive oil. Add sliced onion and cook until tender. Stir in soy sauce and serve hot.",
  "ingredients": ["Beef", "Olive Oil", "Onion", "Soy Sauce"]
}'

post_recipe '{
  "title": "Chickpea Tomato Saute",
  "isVegetarian": true,
  "instructions": "Saute onion and garlic in olive oil. Add chickpeas and tomato. Simmer briefly and season with oregano and salt.",
  "ingredients": ["Chickpeas", "Onion", "Garlic", "Olive Oil", "Tomato", "Oregano", "Salt"]
}'

post_recipe '{
  "title": "Creamy Broccoli Pasta",
  "isVegetarian": true,
  "instructions": "Cook pasta. Steam broccoli lightly. Heat cream with butter, combine with pasta and broccoli, season with salt.",
  "ingredients": ["Pasta", "Broccoli", "Cream", "Butter", "Salt"]
}'

post_recipe '{
  "title": "Pork Ginger Rice Bowl",
  "isVegetarian": false,
  "instructions": "Cook pork in olive oil with ginger and garlic. Serve over cooked rice and drizzle with soy sauce.",
  "ingredients": ["Pork", "Olive Oil", "Ginger", "Garlic", "Rice", "Soy Sauce"]
}'

post_recipe '{
  "title": "Zucchini Bell Pepper Saute",
  "isVegetarian": true,
  "instructions": "Saute zucchini and bell pepper in olive oil. Add garlic, cook until tender, and season with salt.",
  "ingredients": ["Zucchini", "Bell Pepper", "Olive Oil", "Garlic", "Salt"]
}'

post_recipe '{
  "title": "Lentil Carrot Stew",
  "isVegetarian": true,
  "instructions": "Cook lentils with carrot and onion in water until soft. Add thyme and salt before serving.",
  "ingredients": ["Lentils", "Carrot", "Onion", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Fish Herb Bake",
  "isVegetarian": false,
  "instructions": "Place fish in a baking dish. Drizzle olive oil, add rosemary and parsley, season with salt, and bake until flaky.",
  "ingredients": ["Fish", "Olive Oil", "Rosemary", "Parsley", "Salt"]
}'

post_recipe '{
  "title": "Tomato Basil Rice",
  "isVegetarian": true,
  "instructions": "Cook rice and set aside. Saute tomato in olive oil, stir in basil and salt, then mix with rice.",
  "ingredients": ["Rice", "Tomato", "Olive Oil", "Basil", "Salt"]
}'

post_recipe '{
  "title": "Chicken Broccoli Skillet",
  "isVegetarian": false,
  "instructions": "Cook chicken in olive oil until browned. Add broccoli and garlic, cook until tender, and season with salt.",
  "ingredients": ["Chicken", "Olive Oil", "Broccoli", "Garlic", "Salt"]
}'

post_recipe '{
  "title": "Mushroom Thyme Barley",
  "isVegetarian": true,
  "instructions": "Cook barley until tender. Saute mushroom in butter, add thyme and salt, then combine with barley.",
  "ingredients": ["Barley", "Mushroom", "Butter", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Beef Tomato Skillet",
  "isVegetarian": false,
  "instructions": "Brown beef in olive oil. Add chopped tomato and oregano, cook until thickened, and season with salt.",
  "ingredients": ["Beef", "Olive Oil", "Tomato", "Oregano", "Salt"]
}'

post_recipe '{
  "title": "Spinach Chickpea Bowl",
  "isVegetarian": true,
  "instructions": "Saute garlic in olive oil. Add chickpeas and spinach, cook until heated through, and season with paprika and salt.",
  "ingredients": ["Spinach", "Chickpeas", "Garlic", "Olive Oil", "Paprika", "Salt"]
}'

post_recipe '{
  "title": "Pasta with Garlic Oil",
  "isVegetarian": true,
  "instructions": "Cook pasta. Warm olive oil with garlic and chili powder. Toss with pasta and season with salt.",
  "ingredients": ["Pasta", "Olive Oil", "Garlic", "Chili Powder", "Salt"]
}'

post_recipe '{
  "title": "Honey Glazed Pork",
  "isVegetarian": false,
  "instructions": "Cook pork in olive oil. Add honey and rosemary, cook until glazed and season lightly with salt.",
  "ingredients": ["Pork", "Olive Oil", "Honey", "Rosemary", "Salt"]
}'

post_recipe '{
  "title": "Cauliflower Garlic Roast",
  "isVegetarian": true,
  "instructions": "Toss cauliflower with olive oil and garlic. Roast until golden and season with salt.",
  "ingredients": ["Cauliflower", "Olive Oil", "Garlic", "Salt"]
}'

post_recipe '{
  "title": "Chicken Carrot Stew",
  "isVegetarian": false,
  "instructions": "Simmer chicken with carrot and onion until tender. Add thyme and salt before serving.",
  "ingredients": ["Chicken", "Carrot", "Onion", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Quinoa Vegetable Mix",
  "isVegetarian": true,
  "instructions": "Cook quinoa. Saute zucchini and bell pepper in olive oil, mix with quinoa, and season with salt and parsley.",
  "ingredients": ["Quinoa", "Zucchini", "Bell Pepper", "Olive Oil", "Parsley", "Salt"]
}'

post_recipe '{
  "title": "Fish Ginger Soup",
  "isVegetarian": false,
  "instructions": "Simmer fish with ginger and garlic in water until cooked. Season with soy sauce and serve hot.",
  "ingredients": ["Fish", "Ginger", "Garlic", "Soy Sauce"]
}'

post_recipe '{
  "title": "Herb Roasted Chicken and Potatoes",
  "description": "Oven roasted chicken with potatoes and herbs.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Preheat oven and season chicken with salt, pepper, rosemary, and thyme. Toss potato chunks with olive oil, garlic, and a pinch of salt. Arrange chicken and potatoes in a baking dish and roast until the chicken is golden and cooked through, turning the potatoes halfway for even browning. Baste the chicken with pan juices during cooking to keep it moist and flavorful. Let rest briefly before serving to allow juices to redistribute.",
  "ingredients": ["Chicken", "Potato", "Olive Oil", "Garlic", "Salt", "Rosemary", "Thyme"]
}'

post_recipe '{
  "title": "Creamy Garlic Mushroom Chicken",
  "description": "Chicken simmered in a creamy mushroom sauce.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Season chicken with salt and paprika, then sear in olive oil until lightly browned on both sides. Remove and saute mushroom, garlic, and onion in the same pan until softened and fragrant. Stir in cream and a splash of milk, scraping up any browned bits, then return chicken to the pan and simmer gently until fully cooked and the sauce thickens. Finish with chopped parsley and adjust seasoning before serving.",
  "ingredients": ["Chicken", "Mushroom", "Garlic", "Onion", "Cream", "Milk", "Olive Oil", "Salt", "Paprika", "Parsley"]
}'

post_recipe '{
  "title": "Beef and Barley Vegetable Soup",
  "description": "Hearty soup with beef, barley, and vegetables.",
  "servings": 4,
  "isVegetarian": false,
  "instructions": "Brown beef in olive oil with onion and garlic until well seared and fragrant. Add carrot, celery, tomato, and barley, stirring to combine and coat everything in flavor. Pour in water, add bay leaf and thyme, and simmer slowly until the barley is tender and the beef is soft. Skim excess fat if needed and season with salt before removing the bay leaf and serving hot.",
  "ingredients": ["Beef", "Olive Oil", "Onion", "Garlic", "Carrot", "Celery", "Tomato", "Barley", "Bay Leaf", "Thyme", "Salt"]
}'

post_recipe '{
  "title": "Spinach and Cheese Stuffed Pasta Bake",
  "description": "Baked pasta layered with spinach and cheese.",
  "servings": 4,
  "isVegetarian": true,
  "instructions": "Cook pasta in salted water until just al dente, then drain and set aside. Saute garlic and spinach in olive oil until wilted, then combine with cream and shredded cheese to form a rich filling. Layer pasta with the spinach mixture and additional cheese in a baking dish, spreading evenly between layers. Bake until bubbly and golden on top, allowing it to rest slightly before slicing and serving.",
  "ingredients": ["Pasta", "Spinach", "Garlic", "Olive Oil", "Cream", "Cheese", "Salt"]
}'

post_recipe '{
  "title": "Honey Lime Glazed Salmon Style Fish",
  "description": "Pan cooked fish with honey and lime glaze.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Season fish with salt and a touch of paprika, then sear in olive oil until lightly crisp on the outside. In a small bowl mix honey, lime juice, and minced garlic, then pour over the fish in the pan. Allow the glaze to bubble and thicken, spooning it repeatedly over the fish to coat evenly. Cook until the fish flakes easily and the glaze becomes glossy before serving immediately.",
  "ingredients": ["Fish", "Salt", "Paprika", "Olive Oil", "Honey", "Lime", "Garlic"]
}'

post_recipe '{
  "title": "Vegetable Lentil Curry",
  "description": "Spiced lentils with vegetables and coconut milk.",
  "servings": 4,
  "isVegetarian": true,
  "instructions": "Saute onion, garlic, and ginger in olive oil until aromatic and softened. Stir in lentils, tomato, carrot, and chili powder, coating them well in the spices before adding coconut milk and water. Simmer gently until lentils are tender and the mixture thickens into a hearty stew, stirring occasionally to prevent sticking. Finish with salt and chopped cilantro, letting the flavors meld for a few minutes before serving.",
  "ingredients": ["Lentils", "Onion", "Garlic", "Ginger", "Olive Oil", "Tomato", "Carrot", "Chili Powder", "Coconut Milk", "Salt", "Cilantro"]
}'

post_recipe '{
  "title": "Baked Lemon Herb Pork",
  "description": "Oven baked pork with fresh herbs and citrus.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Rub pork with olive oil, salt, rosemary, and thyme, ensuring it is evenly coated on all sides. Place in a baking dish with sliced onion and garlic, then drizzle with fresh lemon juice. Roast until the pork is cooked through and tender, basting occasionally with its own juices for added moisture. Let rest before slicing thinly and spoon the pan juices over the top before serving.",
  "ingredients": ["Pork", "Olive Oil", "Salt", "Rosemary", "Thyme", "Onion", "Garlic", "Lemon"]
}'

post_recipe '{
  "title": "Quinoa and Roasted Vegetable Bowl",
  "description": "Nutritious bowl with quinoa and mixed vegetables.",
  "servings": 3,
  "isVegetarian": true,
  "instructions": "Cook quinoa in salted water until fluffy, then set aside to steam dry. Toss zucchini, bell pepper, broccoli, and carrot with olive oil and salt, spreading them evenly on a baking tray. Roast until tender and slightly caramelized, turning once for even browning. Combine roasted vegetables with quinoa, sprinkle with parsley, and drizzle with a little lemon juice before serving warm.",
  "ingredients": ["Quinoa", "Zucchini", "Bell Pepper", "Broccoli", "Carrot", "Olive Oil", "Salt", "Parsley", "Lemon"]
}'

post_recipe '{
  "title": "Creamy Tomato Basil Chicken",
  "description": "Chicken simmered in tomato cream sauce.",
  "servings": 3,
  "isVegetarian": false,
  "instructions": "Season chicken with salt and sear in olive oil until golden, then remove from the pan. Saute garlic and onion before adding chopped tomato and cooking until softened and slightly thickened. Stir in cream and basil, returning the chicken to the pan to simmer gently until cooked through and coated in sauce. Adjust seasoning and allow the sauce to reduce slightly before serving.",
  "ingredients": ["Chicken", "Salt", "Olive Oil", "Garlic", "Onion", "Tomato", "Cream", "Basil"]
}'

post_recipe '{
  "title": "Garlic Butter Shrimp Style Chicken",
  "description": "Chicken cooked with garlic butter flavors.",
  "servings": 2,
  "isVegetarian": false,
  "instructions": "Season chicken pieces with salt and paprika, then sear in olive oil until lightly browned. Lower the heat and add butter and minced garlic, allowing the butter to melt and infuse the pan. Spoon the garlic butter repeatedly over the chicken as it finishes cooking to build flavor and moisture. Finish with chopped parsley and a squeeze of lemon before serving immediately.",
  "ingredients": ["Chicken", "Salt", "Paprika", "Olive Oil", "Butter", "Garlic", "Parsley", "Lemon"]
}'