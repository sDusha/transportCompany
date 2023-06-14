<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="row g-5">
        <#if storages??>
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4>Список складов</h4>

                <#list storages as storage>
                    <div>
                        <p>${storage.city}</p>
                        <p><a href="/storage/${storage.id}">Подробная информация</a></p>
                    </div><hr>
                </#list>
            </div>
        </#if>
        <#if user.isOwner()>
             <div class="col-md-7 col-lg-8">
            <div class="mb-3 collapse show" id="collapseExample" style="">
                <form action="/storage/create" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title">Название склада</label>
                        <input type="text" required="" class="form-control" id="title" name="title">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="city">Город</label>
                        <input type="text" required="" class="form-control" id="city" name="city">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="address">Адрес</label>
                        <input type="text" required="" class="form-control" id="address" name="address">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="description">Описание склада</label>
                        <textarea required="" class="form-control" id="description" name="description"></textarea>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="contactInfo">Контактная информация</label>
                        <textarea required="" class="form-control" id="contactInfo" name="contactInfo" placeholder="Дополнительный способ связи"></textarea>
                    </div>

                    <input type="hidden" name="_csrf" value="${_csrf.token}">

                    <button type="submit" style="width: 100%" class="btn btn-outline-secondary m5 btn-sm">Добавить</button>
                </form>
            </div>
        </div>
        </#if>

    </div>
</@t.template>