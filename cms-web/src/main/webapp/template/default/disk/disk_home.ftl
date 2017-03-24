<#include "/_inc/_layout.ftl"/>
<#--
<#macro css>
<style type="text/css">ff</style>
</#macro>
-->

<#macro script>
	<script src="${ctx}/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx}/js/content.min.js"></script>
    <script src="${ctx}/js/welcome.min.js"></script>
</#macro> 

<@layout bcls='class="gray-bg"'>
<div class="wrapper wrapper-content">
        <div class="row">
            
            <div class="col-sm-12 animated fadeInRight">
                <div class="mail-box-header">

                    <form method="get" action="http://www.zi-han.net/theme/hplus/index.html" class="pull-right mail-search">
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" name="search" placeholder="搜索邮件标题，正文等">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-sm btn-primary">
                                    搜索
                                </button>
                            </div>
                        </div>
                    </form>
                    <h2>
                    收件箱 (16)
                </h2>
                    <div class="mail-tools tooltip-demo m-t-md">
                        <div class="btn-group pull-right">
                            <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i>
                            </button>
                            <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i>
                            </button>

                        </div>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="刷新邮件列表"><i class="fa fa-refresh"></i> 刷新</button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为已读"><i class="fa fa-eye"></i>
                        </button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为重要"><i class="fa fa-exclamation"></i>
                        </button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为垃圾邮件"><i class="fa fa-trash-o"></i>
                        </button>

                    </div>
                </div>
                <div class="mail-box">

                    <div class="row">
                    <div class="col-sm-12">
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-file"></i>
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>

                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p1.jpg">
                                    </div>
                                    <div class="file-name">
                                        Italy street.jpg
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>

                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p2.jpg">
                                    </div>
                                    <div class="file-name">
                                        My feel.png
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-music"></i>
                                    </div>
                                    <div class="file-name">
                                        Michal Jackson.mp3
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p3.jpg">
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="img-responsive fa fa-film"></i>
                                    </div>
                                    <div class="file-name">
                                        Monica's birthday.mpg4
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <a href="file_manager.html#">
                                <div class="file">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </div>
                                    <div class="file-name">
                                        Annual report 2014.xls
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-file"></i>
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>

                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p1.jpg">
                                    </div>
                                    <div class="file-name">
                                        Italy street.jpg
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>

                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p2.jpg">
                                    </div>
                                    <div class="file-name">
                                        My feel.png
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-music"></i>
                                    </div>
                                    <div class="file-name">
                                        Michal Jackson.mp3
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p3.jpg">
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="img-responsive fa fa-film"></i>
                                    </div>
                                    <div class="file-name">
                                        Monica's birthday.mpg4
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <a href="file_manager.html#">
                                <div class="file">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </div>
                                    <div class="file-name">
                                        Annual report 2014.xls
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-file"></i>
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>

                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p1.jpg">
                                    </div>
                                    <div class="file-name">
                                        Italy street.jpg
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>

                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p2.jpg">
                                    </div>
                                    <div class="file-name">
                                        My feel.png
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-music"></i>
                                    </div>
                                    <div class="file-name">
                                        Michal Jackson.mp3
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="image">
                                        <img alt="image" class="img-responsive" src="img/p3.jpg">
                                    </div>
                                    <div class="file-name">
                                        Document_2014.doc
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <div class="file">
                                <a href="file_manager.html#">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="img-responsive fa fa-film"></i>
                                    </div>
                                    <div class="file-name">
                                        Monica's birthday.mpg4
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="file-box">
                            <a href="file_manager.html#">
                                <div class="file">
                                    <span class="corner"></span>

                                    <div class="icon">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </div>
                                    <div class="file-name">
                                        Annual report 2014.xls
                                        <br/>
                                        <small>添加时间：2014-10-13</small>
                                    </div>
                                </div>
                            </a>
                        </div>

                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
</@layout>
