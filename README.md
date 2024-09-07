
## Closet Application

This application will lets users create an inventory of all their clothing and be able to create clothing outfits with clothing in their closet. 
Users will be able to put together outfits by first choosing inner wear, outer wear, and finally any additional accessories they wish to add. 
People who could use this program are those with messy closets and would like to create an overview of all their clothing as well as
the different outfits that their clothing can create. This project was of interest to me because I have an extremely messy closet and often 
only wear one or two outfits. By creating this application, I can get a clearer view/inventory of my clothes and be able to create many more outfits.

## *User Stories*
- I want to be able to **add** an outfit to my collection
- I want to be able to **view** all my outfits in my collection
- I want to be able to **add** a description to the clothes I add to the inventory
- I want to be able to **separate** my Summer and Winter outfits
- I want to be able to be **save** my outfits and clothes 
- I want to be able to have the option to **load** my outfits upon opening the application


# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple clothes to an inventory" by going to the add clothing tab. Then typing a name, description, and selecting the type of clothes by putting an integer. Finally click the create button. You can then view it in the inventory tab.
- You can then generate an outfit, as long as you have clothes, by going to the "add outfit" tab, and clicking on the clothes you want to add. You will notice that they will become highlighted, indicating that it is part of the current outfit. There can only be one piece of clothing with one type. Then, you can add a name and decide whether to add it to a summer collection or winter collection. Finally, click create. 
- You can locate my visual component on the home tab, of a closet when you open the application
- You can save the state of my application by clicking the save button on the home tab
- You can reload the state of my application by clicking the load button on the home tab


# Phase 2: Task 4
- Added clothing to inventory: White Uniqlo
- Added clothing to inventory: Black pants
- Added summer outfit: Comfy 
- Added White Uniqlo to outfit: Comfy
- Added Black pants to outfit: Comfy

# Phase 3: Task 3
I think in my model package, there were a few functions that
overlapped with one another. Such as adding outfits to an outfit collection.
Specifically in the closet class and outfit collection class. This refactoring would also help with the event logging process.
Since my ui uses both of the outfit adding functions, it made the event logging part of it a bit harder than it needed to be. 
Thus only having one function to create an outfit, it would fix and tidy the code up in the ui package.
