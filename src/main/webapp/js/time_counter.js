var timers = document.getElementsByClassName("time");
// make array of times
var elements = [];
for(var i =0; i < timers.length; i++){
    elements.push(timers[i].innerHTML);
}

// Update the count down every 1 second
var x = setInterval(function() {
    // Get todays date and time
    for (var i=0; i<timers.length; i++){
        var countDownDate = elements[i];
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var distance = countDownDate - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);


        // Output the result in an element with id="demo"
        timers[i].innerHTML = days + "d: " + hours + "h: "
            + minutes + "m: " + seconds + "s";
        // If the count down is over, write some text
        if (distance < 0) {
            timers[i].innerHTML = "EXPIRED";
        }
    }
}, 1000);