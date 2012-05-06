									<div id="footer">
										<p>
											Powered By
											<a href="http://www.mspring.org" title="mspring.org">M-LOG </a>.Theme By
											<a href="http://www.mspring.org" target="_blank">M-Spring</a><br />
											${mspring_config_base_copyright}
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.left-corner, /.right-corner, /#squeeze, /#center -->
					<#include "sidebar.ftl" />
			</div>
			<!-- /container -->
		</div>
		<!-- /layout -->
		<div id="overlay" style="display: none;"></div>

		<script language="JavaScript" type="text/javascript">
		$(document).ready(function(){ 
			$("#mission").load("${path}/themes/${mspring_config_base_theme}/common/note.html");
		});
		</script>
	</body>
</html>