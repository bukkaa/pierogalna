package com.bkk.views;


import com.bkk.components.appnav.AppNav;
import com.bkk.components.appnav.AppNavItem;
import com.bkk.views.about.AboutView;
import com.bkk.views.helloworld.HelloWorldView;
import com.bkk.views.makedumplings.MakedumplingsView;
import com.bkk.views.orderstatus.OrderStatusView;
import com.bkk.views.readytotake.ReadytotakeView;
import com.bkk.views.receipts.ReceiptsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Family Dumlings Factory 'Pierogalnia'");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        nav.addItem(new AppNavItem("Receipts", ReceiptsView.class, LineAwesomeIcon.LIST_OL_SOLID.create()));
        nav.addItem(new AppNavItem("Make dumplings!", MakedumplingsView.class,
                LineAwesomeIcon.ARROW_CIRCLE_RIGHT_SOLID.create()));
        nav.addItem(
                new AppNavItem("Order Status", OrderStatusView.class, LineAwesomeIcon.GRIN_BEAM_SWEAT_SOLID.create()));
        nav.addItem(new AppNavItem("Ready to take!", ReadytotakeView.class,
                LineAwesomeIcon.ARROW_CIRCLE_LEFT_SOLID.create()));
        nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.INFO_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
