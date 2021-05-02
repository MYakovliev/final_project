package com.epam.web.command;

/**
 * This class represents the Result of any implementation of (@link ActionCommand)
 */
public class CommandResult {

    private final String page;
    private final boolean redirect;

    /**
     * Main constructor
     *
     * @param page the path to go to
     * @param redirect is true if the way to get to the page is redirect or false if forward
     */
    private CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    /**
     * Method to create a new result of a command that provides a redirect to exact page
     *
     * @param page to redirect
     * @return new instance of the class
     */
    public static CommandResult createRedirectCommandResult(String page) {
        return new CommandResult(page, true);
    }

    /**
     * Method to create a new result of a command that provides a forward to exact page
     *
     * @param page to forward
     * @return new instance of the class
     */
    public static CommandResult createForwardCommandResult(String page) {
        return new CommandResult(page, false);
    }

    /**
     * getter for attribute page
     *
     * @return the path of a page
     */
    public String getPage() {
        return page;
    }

    /**
     * getter for attribute redirect
     *
     * @return boolean redirect
     */
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommandResult{");
        sb.append("page='").append(page).append('\'');
        sb.append(", redirect=").append(redirect);
        sb.append('}');
        return sb.toString();
    }
}
