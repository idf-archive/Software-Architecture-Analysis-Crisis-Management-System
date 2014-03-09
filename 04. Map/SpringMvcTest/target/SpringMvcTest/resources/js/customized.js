/**
 * Created with PyCharm.
 * User: xuwei
 * Date: 14/10/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */

  $(function() {
    $( ".DateField" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: "1901:2012",
        dateFormat:"yy-mm-dd"
      // You can put more options here.

    });
     $( ".DateField" ).attr('readonly','readonly');

  });
