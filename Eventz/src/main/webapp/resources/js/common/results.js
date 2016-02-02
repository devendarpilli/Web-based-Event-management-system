(function($) {  
      
    $.fn.drawResults = function(params) {  

        params = $.extend( {data : {}}, params);  
       
        this.each(function() {  
        	  var $this = $(this);
        	  if (params.data.length === 0) {
        		  $this.append("<h3>No results found</h3>");
        		  return;
        	  }
        	
              $.each(params.data, function(key, result) {
            	var link = $("<a href='/Eventz/view/" + result.id + "'></a>");
              	var div = $("<div class='result_item'></div>");
              	if (result.image != null && result.image[0])
              		div.append("<img src='"+ result.image[0].medium.url +"' </img>");
              	else if (result.image != null)
              		div.append("<img src='"+ result.image.medium.url +"' </img>");
              	else
              		div.append("<img src='/Eventz/resources/images/default.jpeg' </img>");
              	div.append("<h3>"+ result.title +"</h3>");
              	div.append("On: <b>"+ result.start_time +"</b><br/>");
              	div.append("At: <b>"+ result.venue_name +"</b><br/>");
              	$this.append(link.html(div));
              });
        });  
        
        return this;  
    };  
})(jQuery);   
