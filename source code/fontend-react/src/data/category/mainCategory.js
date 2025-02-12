export const mainCategory = [
  {
    name: "Cake",
    categoryId: "cake",
    level: 1,
    levelTwoCategory:[
        {
            "name": "Flavors",
            "categoryId": "cake_flavor",
            "parentCategoryId":"cake",
            "level":2
        },
        {
            "name": "Occasions",
            "categoryId": "cake_occasions",
            "parentCategoryId":"cake",
            "level":2
        },
        {
            "name": "Types",
            "categoryId": "cake_types",
            "parentCategoryId":"cake",
            "level":2
        },
        {
            "name": "Designs & Themes",
            "categoryId": "cake_Designs_&_Themes",
            "parentCategoryId":"cake",
            "level":2
        }
    ]
  },

  {
    name: "Bread",
    categoryId: "bread",
    level: 1,
    levelTwoCategory: [
        {
            "name": "Types",
            "categoryId": "bread_types",
            "parentCategoryId": "bread",
            "level": 2
        },
        {
            "name": "Flavors",
            "categoryId": "bread_flavors",
            "parentCategoryId": "bread",
            "level": 2
        },
        {
            "name": "Specialty Breads",
            "categoryId": "bread_specialty",
            "parentCategoryId": "bread",
            "level": 2
        }
    ]
},
{
  name: "Cookies",
  categoryId: "cookies",
  level: 1,
  levelTwoCategory: [
      {
          "name": "Types",
          "categoryId": "cookies_types",
          "parentCategoryId": "cookies",
          "level": 2
      },
      {
          "name": "Flavors",
          "categoryId": "cookies_flavors",
          "parentCategoryId": "cookies",
          "level": 2
      },
      {
          "name": "Specialty Cookies",
          "categoryId": "cookies_specialty",
          "parentCategoryId": "cookies",
          "level": 2
      }
  ]
},
  
{
  name: "Dessert",
  categoryId: "dessert",
  level: 1,
  levelTwoCategory: [
      {
          "name": "Types",
          "categoryId": "dessert_types",
          "parentCategoryId": "dessert",
          "level": 2
      },
      {
          "name": "Flavors",
          "categoryId": "dessert_flavors",
          "parentCategoryId": "dessert",
          "level": 2
      },
      {
          "name": "Dietary Preferences",
          "categoryId": "dessert_dietary",
          "parentCategoryId": "dessert",
          "level": 2
      }
  ]
}
];
