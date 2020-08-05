$(function(){

    const appendForm = function(data){
        var formCode = '<a href="#" class="form-link" data-id="' +
            data.id + '">' + data.name +'</a>''<br>';
        var formTwo = '<button class="delete" data-id="' + data.id + '"> X </button>';
        $('#form-list')
            .append('<div>' + formCode + '&emsp;' + formTwo +'</div>');
    };

   //Delete form
           $(document).on('click', '.delete', function(){
               var link = $(this);
               var formId = link.data('id');
               $.ajax({
                   method: "POST",
                   url: '/remove/' + formId,
                   success: function(response)
                   {
                   location.reload();
                   },
                   error: function(response)
                   {
                       if(response.status == 404) {
                           alert('Форма не найдена!');
                       }
                   }
               });
                   return false;
           });

    //Loading books on load page
    $.get('/forms/', function(response)
    {
        for(i in response) {
            appendForm(response[i]);
        }
    });

    //Show adding book form
    $('#show-add-form-form').click(function(){
        $('#form-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#form-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.form-link', function(){
        var link = $(this);
        var formId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/forms/' + formId,
            success: function(response)
            {
                var code = '</span><br><b>Фамилия Имя Отчество: </b>' + response.name
                +'</span><br><b>Паспортные данные: </b>' + response.pasport
                +'</span><br><b>Адрес постоянной регистрации: </b>' + response.adress
                +'</span><br><b>Запрашиваемая сумма: </b>' + response.summ
                +'</span><br><b>Срок кредита: </b>' + response.time +'</span><br>';

                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Форма не найдена!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-form').click(function()
    {
        var data = $('#form-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/forms/',
            data: data,
            success: function(response)
            {
                $('#form-form').css('display', 'none');
                var form = {};
                form.id = response;
                var dataArray = $('#form-form form').serializeArray();
                for(i in dataArray) {
                    form[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendForm(form);
            }
        });
        return false;
    });

});