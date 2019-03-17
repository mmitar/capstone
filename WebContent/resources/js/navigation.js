function myFunction() {
  let navbar = document.getElementById("myTopnav");
  if (navbar.className === "topnav") {
    navbar.className += " responsive";
  } else {
    navbar.className = "topnav";
  }
}

function bindItem() 
{
  // Grab all the item_menu.
  let itemsArray = document.getElementsByClassName("item-head");
  
  // iterate through all and add an event listener
  for(let i = 0; i < itemsArray.length; i++)
  {
    itemsArray[i].addEventListener("click", function() {toggleItem(itemsArray[i]) }, false);
  }
}

function toggleItem(item_menu_elem) 
{
  // Step out of menu to header to whole item, look for body
  // let item_body = item_menu_elem.parentElement.
  // parentElement.querySelector(".item-body");
  
  let item_body = item_menu_elem.parentElement.querySelector(".item-body");
  
  // Get body display value, toggle visibility on and off
  if(getComputedStyle(item_body, null).display === "flex") 
  {
    item_body.style.display = "none";
  } else
  {
    item_body.style.display = "flex";
  }
}

/** ====================================================== **/
/** ============ DOCUMENT READY EVENT LISTENER =========== **/
/** ====================================================== **/
document.addEventListener("DOMContentLoaded", function(event) {
	  console.log("DOM fully loaded and parsed");
	    
	  // Check the view if item-heads exist to enable on-click display
	  if(document.querySelector(".item-head")) { bindItem(); }
	  
	  
	  
});
