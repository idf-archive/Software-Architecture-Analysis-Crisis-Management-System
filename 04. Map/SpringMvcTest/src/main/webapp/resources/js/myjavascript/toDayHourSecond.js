var DAYSEC = 60*60*24;
var HOURSEC = 60*60;
var MINUTESEC = 60;

var current_time_in_sec= new Date().getTime()/1000;

var birthday_in_sec = new Date('1992/10/07 ' + '10:00:00').getTime() / 1000;
var live_for = current_time_in_sec - birthday_in_sec;
var count=parseInt(live_for, 10);

function toDayHourSecond(seconds){

	var day = parseInt(seconds/(DAYSEC), 10);
	var hour = parseInt((seconds-day*DAYSEC )/(HOURSEC), 10);
	var minute = parseInt((seconds-day*DAYSEC-hour*HOURSEC)/MINUTESEC , 10);
	var second = seconds - day*DAYSEC - hour*HOURSEC - minute*MINUTESEC;
	
	var array = new Object();
	array["day"]=day;
	array["hour"]=hour;
	array["minute"]=minute;
	array["second"]=second;
	//@DONE @TODO another data structure rather than list
	return array;
}


var counter=setInterval(timer, 1000); //1000 will  run it every 1 second
function timer(){
  	count=count+1;
  	if (count <= 0){
     clearInterval(counter);
     return;
  	}
  	var time_array = toDayHourSecond(count);
  	var str = time_array["day"]+" days "+time_array["hour"]+" hours "+time_array["minute"]+" minutes "+time_array["second"]+" secs."
  	document.getElementById("timer").innerHTML=str; 
}

//set the percentage
//must put below the <body></body> to alter the html element
var age = 83;
var percentage = 0.4;
var deathday_in_sec = new Date((1992+age)+'/10/07 ' + '10:00:00').getTime() / 1000;
percentage = (current_time_in_sec - birthday_in_sec)/(deathday_in_sec - birthday_in_sec) ;



var percentage_str = Number(percentage*100).toFixed(2)+"%"
document.getElementById("life_progressbar").style.width=percentage_str; //style store as dictionary
document.getElementById("life_progressbar_span").innerHTML=
"The life of Danyang is <b><i>"+percentage_str+"</i></b> complete."; //can use tag in innerHTML


var remaining_days = (deathday_in_sec - current_time_in_sec)/DAYSEC;
document.getElementById("remaining_days").innerHTML=Number(remaining_days).toFixed(0)+" days";

