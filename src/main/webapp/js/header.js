function openList() {
    let x = document.getElementById("topnavElement");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}