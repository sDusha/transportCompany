<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if user.email??>
        <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4>Список контрактов</h4>
                <#if user.isAdmin()>
                    <h5>Поиск по номеру телефона</h5>
                    <form action="/contracts" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="phone"></label>
                            <input required="" class="form-control" id="phone" name="phone"
                                   placeholder="Номер телефона">
                        </div>
                        <br>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">

                        <button type="submit" style="width: 100%" class="btn btn-outline-secondary m5 btn-sm">Найти</button>
                    </form>
                </#if>
                <#if user.isOwner()>
                    <h5>Поиск по номеру телефона</h5>
                    <form action="/contracts" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="phone"></label>
                            <input required="" class="form-control" id="phone" name="phone"
                                   placeholder="Номер телефона">
                        </div>
                        <br>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">

                        <button type="submit" style="width: 100%" class="btn btn-outline-secondary m5 btn-sm">Найти</button>
                    </form>
                </#if>
                <#if contracts??>
                            <#list contracts as contract>
                                <div>
                                    <p>Откуда: ${contract.fromAddress}, Куда: ${contract.toAddress}</p>
                                    <p><a href="/contract/${contract.id}">Подробная информация</a></p>
                                </div><hr>
                            </#list>
                    <#else>
                    Такого номера не существует
                </#if>
            </div>
            <#if storages??>
               <div class="col-md-7 col-lg-8" >
                <form action="/contract/create" method="post" enctype="multipart/form-data">
                    <div>
                        <label for="special">Город отправления</label>
                        <#list specials as special>
                            <div>
                                <label><input type="checkbox"
                                              name="${special}" ${user.roles?seq_contains(special)?string("checked", "")}>${special}
                                </label>
                            </div>
                        </#list>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="fromAddress">Город отправления</label>
                        <select id="fromAddress" required="" name="fromAddress" class="form-control">
                            <option value="">Город не выбран</option>
                            <#list storages as storage>
                                <option value="${storage.city}, ${storage.address}">${storage.city}
                                    , ${storage.address}</option>
                            </#list>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="toAddress">Город прибытия</label>
                        <select id="toAddress" required="" name="toAddress" class="form-control">
                            <option value="">Город не выбран</option>
                            <#list storages as storage>
                                <option value="${storage.city}, ${storage.address}">${storage.city}
                                    , ${storage.address}</option>
                            </#list>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="weight">Вес</label>
                        <input required="" class="form-control" id="weight" name="weight"
                               placeholder="Вес">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="Объем">Вес</label>
                        <input required="" class="form-control" id="volume" name="volume" placeholder="Объем">
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="contactInfo">Контактная информация</label>
                        <textarea required="" class="form-control" id="contactInfo" name="contactInfo"
                                  placeholder="Дополнительный способ связи"></textarea>
                    </div>
                    <br>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">

                    <button type="submit" style="width: 100%" class="btn btn-outline-secondary m5 btn-sm">Добавить</button>
                </form>
            </div>
            </#if>
        </div>
    </#if>
</@t.template>